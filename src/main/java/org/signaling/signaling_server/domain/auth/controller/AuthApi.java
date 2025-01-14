package org.signaling.signaling_server.domain.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.domain.auth.dto.response.SignInResponse;
import org.springframework.security.core.Authentication;

@Tag(name = "Auth Api", description = "인증 관련 API 목록입니다.")
public interface AuthApi {
    @Operation(summary = "로그아웃을 합니다.", description = "담당자: 최민석")
    Api<SignInResponse> signOut(Authentication authentication, String accessToken);
}