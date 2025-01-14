package org.signaling.signaling_server.domain.auth.mapper;

import org.signaling.signaling_server.domain.auth.dto.request.SignUpRequest;
import org.signaling.signaling_server.entity.member.MemberEntity;
import org.signaling.signaling_server.redis.RefreshToken;

import java.time.LocalDateTime;

public class RefreshTokenEntityMapper {
    public static RefreshToken of(Long memberId, String refreshToken){
        return RefreshToken.builder()
                .refreshToken(refreshToken)
                .refreshToken(refreshToken)
                .build();
    }
}
