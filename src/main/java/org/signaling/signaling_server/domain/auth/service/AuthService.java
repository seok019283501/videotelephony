package org.signaling.signaling_server.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.common.exception.BadRequestException;
import org.signaling.signaling_server.common.exception.NotFoundException;
import org.signaling.signaling_server.common.type.error.AuthErrorType;
import org.signaling.signaling_server.common.utils.JwtUtils;
import org.signaling.signaling_server.domain.auth.dto.request.SignInRequest;
import org.signaling.signaling_server.domain.auth.dto.request.SignUpRequest;
import org.signaling.signaling_server.domain.auth.dto.response.SignInResponse;
import org.signaling.signaling_server.domain.auth.mapper.AuthEntityMapper;
import org.signaling.signaling_server.domain.auth.mapper.AuthResponseMapper;
import org.signaling.signaling_server.domain.auth.mapper.TokenEntityMapper;
import org.signaling.signaling_server.domain.member.dto.CustomUserDetail;
import org.signaling.signaling_server.domain.member.repository.MemberRepository;
import org.signaling.signaling_server.entity.member.MemberEntity;
import org.signaling.signaling_server.redis.AccessToken;
import org.signaling.signaling_server.redis.AccessTokenRepository;
import org.signaling.signaling_server.redis.RefreshToken;
import org.signaling.signaling_server.redis.RefreshTokenRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AccessTokenRepository accessTokenRepository;

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

        //entity 전환
        MemberEntity memberEntity = AuthEntityMapper.toEntity(signUpRequest);

        //회원저장
        memberRepository.save(memberEntity);
    }

    //로그인
    public SignInResponse signIn(SignInRequest signInRequest) {
        MemberEntity memberEntity = memberRepository.findByUsername(signInRequest.username())
                .orElseThrow(()-> new NotFoundException(AuthErrorType.NOT_FOUND));

        //비밀번호 비교
        if(bCryptPasswordEncoder.matches(signInRequest.password(),memberEntity.getPassword())){
            throw new BadRequestException(AuthErrorType.PASSWORD_NOT_MATCH);
        }

        //access token, refresh token 생성
        String accessToken = jwtUtils.generateAccessToken(memberEntity.getName(), memberEntity.getId());
        String refreshToken = jwtUtils.generateRefreshToken(memberEntity.getName(), memberEntity.getId());

        //refresh token redis 저장
        RefreshToken refreshTokenEntity = TokenEntityMapper.toRefreshToken(memberEntity.getId(), refreshToken);
        refreshTokenRepository.save(refreshTokenEntity);

        accessToken = jwtUtils.includeBearer(accessToken);
        refreshToken = jwtUtils.includeBearer(refreshToken);

        return AuthResponseMapper.from(accessToken,refreshToken);
    }

    //로그아웃
    public void signOut(Authentication authentication, String accessToken) {
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();

        //redis의 블랙리스트로 access token이 있는 경우
        if (accessTokenRepository.existsByAccessToken(accessToken)) {
            return;
        }

        //refresh token 화이트 리스트 제거
        refreshTokenRepository.deleteByMemberId(userDetails.getId());

        accessToken = accessToken.substring(7);

        //access token 남은 시간 계산
        Date date = jwtUtils.getExpirationDateFromToken(accessToken);
        long ttl = (date.getTime() - System.currentTimeMillis()) / 1000;

        AccessToken accessTokenEntity = TokenEntityMapper.toAccessToken(ttl,accessToken);

        //access token 블랙리스트 저장
        accessTokenRepository.save(accessTokenEntity);
    }
}
