package org.signaling.signaling_server.domain.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.domain.auth.dto.request.SignInRequest;
import org.signaling.signaling_server.domain.auth.dto.request.SignUpRequest;
import org.signaling.signaling_server.domain.auth.dto.response.SignInResponse;
import org.springframework.security.core.Authentication;

@Tag(name = "Auth Open Api", description = "인증 관련 Open API 목록입니다.")
public interface AuthOpenApi {
    @Operation(summary = "회원가입을 합니다.", description = "담당자: 최민석")
    Api<?> signUp(SignUpRequest signUpRequest);

    @Operation(summary = "로그인을 합니다.", description = "담당자: 최민석")
    Api<SignInResponse> signIn(SignInRequest signInRequest);
}
