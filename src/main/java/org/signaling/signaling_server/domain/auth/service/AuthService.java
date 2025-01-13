package org.signaling.signaling_server.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.common.exception.ApiException;
import org.signaling.signaling_server.common.exception.ApiExceptionImpl;
import org.signaling.signaling_server.common.exception.BadRequestException;
import org.signaling.signaling_server.common.type.error.AuthErrorType;
import org.signaling.signaling_server.domain.auth.dto.request.SignUpRequest;
import org.signaling.signaling_server.domain.auth.mapper.AuthEntityMapper;
import org.signaling.signaling_server.domain.member.repository.MemberRepository;
import org.signaling.signaling_server.entity.member.MemberEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;

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
        MemberEntity memberEntity = AuthEntityMapper.of(signUpRequest);

        //회원저장
        memberRepository.save(memberEntity);
    }
}
