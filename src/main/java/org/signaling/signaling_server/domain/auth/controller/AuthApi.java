package org.signaling.signaling_server.domain.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.domain.auth.dto.request.ChangePasswordRequest;
import org.signaling.signaling_server.domain.auth.dto.response.ReissueAccessTokenResponse;
import org.signaling.signaling_server.domain.auth.dto.response.SignInResponse;
import org.springframework.security.core.Authentication;

@Tag(name = "Auth Api", description = "인증 관련 API 목록입니다.")
public interface AuthApi {
    @Operation(summary = "로그아웃을 합니다.", description = "담당자: 최민석")
    Api<SignInResponse> signOut(String accessToken);

    @Operation(summary = "Access Token을 재발급합니다.", description = "담당자: 최민석")
    Api<ReissueAccessTokenResponse> reissueAccessToken(Authentication authentication, String refreshToken);

    @Operation(summary = "비밀번호 변경을 합니다.", description = "담당자: 최민석")
    Api<?> changePassword(Authentication authentication, ChangePasswordRequest changePasswordRequest);
}
