package org.signaling.signaling_server.domain.auth.mapper;

import org.signaling.signaling_server.domain.auth.dto.request.SignUpRequest;
import org.signaling.signaling_server.entity.member.MemberEntity;

import java.time.LocalDateTime;

public class AuthEntityMapper {

    public static MemberEntity of(SignUpRequest signUpRequest){
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
}
