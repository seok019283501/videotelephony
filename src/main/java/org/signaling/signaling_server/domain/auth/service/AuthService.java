package org.signaling.signaling_server.domain.auth.service;

import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.signaling.signaling_server.common.exception.BadRequestException;
import org.signaling.signaling_server.common.exception.InternalServerException;
import org.signaling.signaling_server.common.exception.NotFoundException;
import org.signaling.signaling_server.common.exception.UnauthorizedException;
import org.signaling.signaling_server.common.type.error.AuthErrorType;
import org.signaling.signaling_server.common.utils.JwtUtils;
import org.signaling.signaling_server.domain.auth.dto.request.EmailRequest;
import org.signaling.signaling_server.domain.auth.dto.request.SignInRequest;
import org.signaling.signaling_server.domain.auth.dto.request.SignUpRequest;
import org.signaling.signaling_server.domain.auth.dto.request.VerificationCodeRequest;
import org.signaling.signaling_server.domain.auth.dto.response.ReissueAccessTokenResponse;
import org.signaling.signaling_server.domain.auth.dto.response.SignInResponse;
import org.signaling.signaling_server.domain.auth.mapper.AuthEntityMapper;
import org.signaling.signaling_server.domain.auth.mapper.AuthResponseMapper;
import org.signaling.signaling_server.domain.auth.mapper.TokenEntityMapper;
import org.signaling.signaling_server.domain.member.dto.CustomUserDetail;
import org.signaling.signaling_server.domain.member.repository.MemberRepository;
import org.signaling.signaling_server.entity.member.MemberEntity;
import org.signaling.signaling_server.redis.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AccessTokenRepository accessTokenRepository;
    private final JavaMailSender javaMailSender;
    private final EmailCodeRepository emailCodeRepository;
    private static final SecureRandom rd = new SecureRandom();

    //회원가입
    public void signUp(SignUpRequest signUpRequest){

        //이메일 중복 확인
        if(memberRepository.existsByEmail(signUpRequest.email())){
            throw new BadRequestException(AuthErrorType.EMAIL_DUPLICATED);
        }

        //username 중복 확인
        if(memberRepository.existsByUsername(signUpRequest.username())){
            throw new BadRequestException(AuthErrorType.USERNAME_DUPLICATED);
        }

        // 비밀번호 확인
        if(!signUpRequest.password().equals(signUpRequest.passwordConfirm())){
            throw new BadRequestException(AuthErrorType.PASSWORD_NOT_MATCH);
        }
        String encodedPassword = bCryptPasswordEncoder.encode(signUpRequest.password());
        //entity 전환
        MemberEntity memberEntity = AuthEntityMapper.toEntity(signUpRequest,encodedPassword);

        //회원저장
        memberRepository.save(memberEntity);
    }

    //로그인
    @Transactional
    public SignInResponse signIn(SignInRequest signInRequest) {
        MemberEntity memberEntity = memberRepository.findByUsername(signInRequest.username())
                .orElseThrow(()-> new NotFoundException(AuthErrorType.NOT_FOUND));

        //비밀번호 비교
        if(bCryptPasswordEncoder.matches(memberEntity.getPassword(),signInRequest.password())){
            throw new BadRequestException(AuthErrorType.PASSWORD_NOT_MATCH);
        }

        //access token, refresh token 생성
        String accessToken = jwtUtils.generateAccessToken(memberEntity.getName(), memberEntity.getId());
        String refreshToken = jwtUtils.generateRefreshToken(memberEntity.getName(), memberEntity.getId());

        //refresh token redis 저장
        RefreshToken refreshTokenEntity = TokenEntityMapper.toRefreshToken(accessToken, refreshToken);
        refreshTokenRepository.save(refreshTokenEntity);

        accessToken = jwtUtils.includeBearer(accessToken);
        refreshToken = jwtUtils.includeBearer(refreshToken);

        return AuthResponseMapper.toSignInResponse(accessToken,refreshToken);
    }

    //로그아웃
    public void signOut(Authentication authentication, String accessToken) {
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();

        //redis의 블랙리스트로 access token이 있는 경우
        if (accessTokenRepository.existsByAccessToken(accessToken)) {
            return;
        }

        //refresh token 화이트 리스트 제거
        refreshTokenRepository.deleteByAccessToken(accessToken);

        accessToken = accessToken.substring(7);

        //access token 남은 시간 계산
        Date date = jwtUtils.getExpirationDateFromToken(accessToken);
        long ttl = (date.getTime() - System.currentTimeMillis()) / 1000;

        AccessToken accessTokenEntity = TokenEntityMapper.toAccessToken(ttl,accessToken);

        //access token 블랙리스트 저장
        accessTokenRepository.save(accessTokenEntity);
    }

    @Transactional
    //토큰 재발급
    public ReissueAccessTokenResponse ReissueAccessToken(Authentication authentication, String refreshToken) {
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();

        refreshToken = refreshToken.substring(7);

        //refresh token 유무 확인
        RefreshToken refreshTokenEntity = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()->new UnauthorizedException(AuthErrorType.TOKEN_NOT_FOUND));

        String accessToken = refreshTokenEntity.getAccessToken();

        //access token 남은 시간 계산
        Date date = jwtUtils.getExpirationDateFromToken(accessToken);
        long ttl = (date.getTime() - System.currentTimeMillis()) / 1000;

        AccessToken accessTokenEntity = TokenEntityMapper.toAccessToken(ttl,accessToken);

        //기존 access token 블랙리스트
        accessTokenRepository.save(accessTokenEntity);

        //access token 재발급
        String newAccessToken = jwtUtils.generateAccessToken(userDetails.getMemberEntity().getUsername(), userDetails.getId());

        //refreshTokenEntity accessToken 변경
        RefreshToken newRefreshTokenEntity = TokenEntityMapper.toRefreshToken(newAccessToken,refreshToken);

        refreshTokenRepository.save(newRefreshTokenEntity);

        newAccessToken = jwtUtils.includeBearer(newAccessToken);

        return AuthResponseMapper.toReissueAccessTokenResponse(newAccessToken);
    }

    public String getCode(){
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<7;i++){
            sb.append((char)(rd.nextInt(26)+65));
        }

        return sb.toString();
    }

    @Transactional
    //인증코드 발송
    public void sendCode(EmailRequest emailRequest) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //이메일 중복 확인
        if(memberRepository.existsByEmail(emailRequest.email())){
            throw new BadRequestException(AuthErrorType.EMAIL_DUPLICATED);
        }

        String code = getCode();

        EmailCode emailCode = AuthEntityMapper.toEmailCode(emailRequest.email(),code);

        //코드 redis 저장
        emailCodeRepository.save(emailCode);

        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            // 메일을 받을 수신자 설정
            mimeMessageHelper.setTo(emailRequest.email());
            // 메일의 제목 설정
            mimeMessageHelper.setSubject("VISION CALL 메일 전송");

            // html 문법 적용한 메일의 내용
            String content = """
                    <!DOCTYPE html>
                    <html xmlns:th="http://www.thymeleaf.org">    
                    <body>
                    <div style="margin:100px;">
                        <h1> 인증코드 </h1>
                        <br>          
                        <div align="center" style="border:1px solid black;">
                            <h3> %s </h3>
                        </div>
                        <br/>
                    </div>          
                    </body>
                    </html>
                    """.formatted(code);;

            // 메일의 내용 설정
            mimeMessageHelper.setText(content, true);

            javaMailSender.send(mimeMessage);

            log.info("메일 발송 성공");
        } catch (Exception e) {
            log.info("메일 발송 실패");
            throw new InternalServerException(AuthErrorType.EMAIL_SEND_FAIL);
        }
    }

    //인증코드 검증
    public void checkVerification(VerificationCodeRequest verificationCodeRequest) {


        EmailCode emailCode = emailCodeRepository.findByEmail(verificationCodeRequest.email())
                .orElseThrow(()->new BadRequestException(AuthErrorType.EMAIL_NOT_MATCH));

        //코드 검증
        if(!emailCode.getCode().equals(verificationCodeRequest.code())){
            throw new BadRequestException(AuthErrorType.CODE_NOT_MATCH);
        }
    }

    //임시 비밀번호 발급
    @Transactional
    public void issuePassword(EmailRequest emailRequest) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //이메일 확인
        if(!memberRepository.existsByEmail(emailRequest.email())){
            throw new BadRequestException(AuthErrorType.EMAIL_NOT_MATCH);
        }

        String ispwd = getIssuePassword();

        String encodedPassword = bCryptPasswordEncoder.encode(ispwd);

        memberRepository.updateByPassword(encodedPassword,emailRequest.email());

        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            // 메일을 받을 수신자 설정
            mimeMessageHelper.setTo(emailRequest.email());
            // 메일의 제목 설정
            mimeMessageHelper.setSubject("VISION CALL 메일 전송");

            // html 문법 적용한 메일의 내용
            String content = """
                    <!DOCTYPE html>
                    <html xmlns:th="http://www.thymeleaf.org">    
                    <body>
                    <div style="margin:100px;">
                        <h1> 임시 비밀번호 </h1>
                        <br>          
                        <div align="center" style="border:1px solid black;">
                            <h3> %s </h3>
                        </div>
                        <br/>
                    </div>          
                    </body>
                    </html>
                    """.formatted(ispwd);;

            // 메일의 내용 설정
            mimeMessageHelper.setText(content, true);

            javaMailSender.send(mimeMessage);

            log.info("메일 발송 성공");
        } catch (Exception e) {
            log.info("메일 발송 실패");
            throw new InternalServerException(AuthErrorType.EMAIL_SEND_FAIL);
        }
    }

    public String getIssuePassword(){

        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialCharacters = "!@#$%^&*()-_+=<>?";
        String allCharacters = uppercase + lowercase + digits + specialCharacters;

        StringBuilder password = new StringBuilder(8);

        // 적어도 한 글자씩 포함되도록 보장
        password.append(uppercase.charAt(rd.nextInt(uppercase.length())));
        password.append(lowercase.charAt(rd.nextInt(lowercase.length())));
        password.append(digits.charAt(rd.nextInt(digits.length())));
        password.append(specialCharacters.charAt(rd.nextInt(specialCharacters.length())));

        // 나머지 글자는 모든 문자 중에서 랜덤하게 선택
        for (int i = 4; i < 8; i++) {
            password.append(allCharacters.charAt(rd.nextInt(allCharacters.length())));
        }

        // 섞어서 랜덤성을 더함
        return shuffleString(password.toString());
    }

    String shuffleString(String input) {
        char[] array = input.toCharArray();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rd.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        return new String(array);
    }
}
