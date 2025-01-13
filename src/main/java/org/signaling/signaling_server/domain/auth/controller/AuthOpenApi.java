package org.signaling.signaling_server.domain.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.domain.auth.dto.request.SignUpRequest;

@Tag(name = "Auth Api", description = "인증 관련 API 목록입니다.")
public interface AuthOpenApi {
    @Operation(summary = "회원가입을 합니다.", description = "담당자: 최민석")
    Api<?> signUp(SignUpRequest signUpRequest);
}
