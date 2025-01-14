package org.signaling.signaling_server.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.common.type.success.AuthSuccessType;
import org.signaling.signaling_server.domain.auth.dto.request.SignInRequest;
import org.signaling.signaling_server.domain.auth.dto.request.SignUpRequest;
import org.signaling.signaling_server.domain.auth.dto.response.SignInResponse;
import org.signaling.signaling_server.domain.auth.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api/auth")
public class AuthOpenApiController {
    private final AuthService authService;
    @PostMapping("/sign-up")
    public Api<?> signUp(
            @Valid
            @RequestBody SignUpRequest signUpRequest
    ){
        authService.signUp(signUpRequest);
        return Api.success(AuthSuccessType.SIGN_UP);
    }

    @PostMapping("/sign-in")
    public Api<SignInResponse> signIn(
            @Valid
            @RequestBody SignInRequest signInRequest
    ){
            SignInResponse signInResponse = authService.signIn(signInRequest);
            return Api.success(AuthSuccessType.SIGN_IN,signInResponse);
    }
}
