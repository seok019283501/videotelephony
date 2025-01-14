package org.signaling.signaling_server.domain.auth.mapper;

import org.signaling.signaling_server.domain.auth.dto.response.SignInResponse;

public class AuthResponseMapper {
    public static SignInResponse from(String accessToken, String refreshToken){
        return SignInResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
