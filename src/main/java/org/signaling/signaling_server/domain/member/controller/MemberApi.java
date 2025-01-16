package org.signaling.signaling_server.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.domain.auth.dto.response.SignInResponse;
import org.signaling.signaling_server.domain.member.dto.response.MemberInfoResponse;
import org.springframework.security.core.Authentication;

@Tag(name = "Member Api", description = "회원 관련 API 목록입니다.")
public interface MemberApi {
    @Operation(summary = "회원정보를 조회합니다.", description = "담당자: 최민석")
    Api<MemberInfoResponse> memberInfo(Authentication authentication);
}
