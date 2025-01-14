package org.signaling.signaling_server.domain.auth.mapper;

import org.signaling.signaling_server.domain.auth.dto.response.ReissueAccessTokenResponse;
import org.signaling.signaling_server.domain.auth.dto.response.SignInResponse;

public class AuthResponseMapper {
    public static SignInResponse toSignInResponse(String accessToken, String refreshToken){
        return SignInResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public static ReissueAccessTokenResponse toReissueAccessTokenResponse(String accessToken){
        return ReissueAccessTokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
