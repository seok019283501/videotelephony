package org.signaling.signaling_server.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.common.type.success.AuthSuccessType;
import org.signaling.signaling_server.domain.auth.dto.request.ChangePasswordRequest;
import org.signaling.signaling_server.domain.auth.dto.response.ReissueAccessTokenResponse;
import org.signaling.signaling_server.domain.auth.dto.response.SignInResponse;
import org.signaling.signaling_server.domain.auth.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthApiController implements AuthApi{
    private final AuthService authService;

    //로그아웃
    @PostMapping("/sign-out")
    public Api<SignInResponse> signOut(@RequestHeader("Authorization") String accessToken) {
        authService.signOut(accessToken);
        return Api.success(AuthSuccessType.SIGN_OUT);
    }

    // Acess Token 토큰 재발급
    @PostMapping("/reissue-token")
    public Api<ReissueAccessTokenResponse> reissueAccessToken(Authentication authentication, @RequestHeader("Authorization") String refreshToken) {
        ReissueAccessTokenResponse reissueAccessToken = authService.ReissueAccessToken(authentication, refreshToken);
        return Api.success(AuthSuccessType.REISSUE_ACCESS_TOKEN,reissueAccessToken);
    }

    @PatchMapping("/change-password")
    public Api<?> changePassword(
            Authentication authentication,
            @Valid
            @RequestBody ChangePasswordRequest changePasswordRequest
    ) {
        authService.changePassword(authentication,changePasswordRequest);
        return Api.success(AuthSuccessType.CHANGE_PASSWORD);
    }
}
