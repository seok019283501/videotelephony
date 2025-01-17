package org.signaling.signaling_server.domain.auth.mapper;

import org.signaling.signaling_server.redis.AccessToken;
import org.signaling.signaling_server.redis.RefreshToken;

public class TokenEntityMapper {
    public static RefreshToken toRefreshToken(String accessToken, String refreshToken){
        return RefreshToken.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build();
    }
    public static AccessToken toAccessToken(Long ttl, String accessToken){
        return AccessToken.builder()
                .accessToken(accessToken)
                .ttl(ttl)
                .build();
    }
}
