package org.signaling.signaling_server.domain.auth.mapper;

import org.signaling.signaling_server.domain.auth.dto.request.SignUpRequest;
import org.signaling.signaling_server.entity.member.MemberEntity;
import org.signaling.signaling_server.redis.EmailCode;

import java.time.LocalDateTime;

public class AuthEntityMapper {

    public static MemberEntity toEntity(SignUpRequest signUpRequest){
        return MemberEntity.builder()
                .name(signUpRequest.name())
                .birth(signUpRequest.birth())
                .email(signUpRequest.email())
                .birth(signUpRequest.birth())
                .nickname(signUpRequest.nickname())
                .password(signUpRequest.password())
                .username(signUpRequest.username())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static EmailCode toEmailCode(String email, String code){
        return EmailCode.builder()
                .email(email)
                .code(code)
                .build();
    }
}
