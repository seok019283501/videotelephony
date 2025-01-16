package org.signaling.signaling_server.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.common.type.success.AuthSuccessType;
import org.signaling.signaling_server.domain.auth.dto.request.EmailRequest;
import org.signaling.signaling_server.domain.auth.dto.request.SignInRequest;
import org.signaling.signaling_server.domain.auth.dto.request.SignUpRequest;
import org.signaling.signaling_server.domain.auth.dto.request.VerificationCodeRequest;
import org.signaling.signaling_server.domain.auth.dto.response.FindUsernameResponse;
import org.signaling.signaling_server.domain.auth.dto.response.SignInResponse;
import org.signaling.signaling_server.domain.auth.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api/auth")
public class AuthOpenApiController implements AuthOpenApi {
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

    @PostMapping("/email-verification")
    public Api<?> sendCode(
            @Valid
            @RequestBody EmailRequest emailRequest
    ){
        authService.sendCode(emailRequest);
        return Api.success(AuthSuccessType.EMAIL_SEND_SUCCESS);
    }


    @PostMapping("/check-verification")
    public Api<?> checkVerification(
            @Valid
            @RequestBody VerificationCodeRequest verificationCodeRequest
    ) {
        authService.checkVerification(verificationCodeRequest);
        return Api.success(AuthSuccessType.EMAIL_VERIFICATION);
    }

    @PatchMapping("/reset-password")
    public Api<?> issuePassword(
            @Valid
            @RequestBody EmailRequest emailRequest
    ) {
        authService.issuePassword(emailRequest);
        return Api.success(AuthSuccessType.ISSUE_PASSWORD);
    }

    @GetMapping("/find-username")
    public Api<FindUsernameResponse> findUsername(
            @Valid
            @RequestBody EmailRequest emailRequest
    ) {
        FindUsernameResponse findUsernameResponse = authService.findUsername(emailRequest);
        return Api.success(AuthSuccessType.FIND_USERNAME,findUsernameResponse);
    }
}
