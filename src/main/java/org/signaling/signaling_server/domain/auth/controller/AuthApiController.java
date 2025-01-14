package org.signaling.signaling_server.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.common.type.success.AuthSuccessType;
import org.signaling.signaling_server.domain.auth.dto.response.SignInResponse;
import org.signaling.signaling_server.domain.auth.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthApiController implements AuthApi{
    private final AuthService authService;
    @PostMapping("/sign-out")
    public Api<SignInResponse> signOut(Authentication authentication, @RequestHeader("Authorization") String accessToken) {
        authService.signOut(authentication,accessToken);
        return Api.success(AuthSuccessType.SIGN_OUT);
    }
}
