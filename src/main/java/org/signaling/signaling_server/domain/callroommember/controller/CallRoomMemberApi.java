package org.signaling.signaling_server.domain.callroommember.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.domain.callroommember.dto.request.ExpulsionMemberRequest;
import org.signaling.signaling_server.domain.callroommember.dto.request.InviteMemberIdRequest;
import org.springframework.security.core.Authentication;

@Tag(name = "CallRoomMember Api", description = "통화방 회원 관련 API 목록입니다.")
public interface CallRoomMemberApi {
    @Operation(summary = "통화방에 회원을 초대합니다.", description = "담당자: 최민석")
    Api<?> inviteRoom(InviteMemberIdRequest inviteMemberIdRequest, Authentication authentication);

    @Operation(summary = "통화방에 회원을 퇴출합니다.", description = "담당자: 최민석")
    Api<?> expulsionRoom(ExpulsionMemberRequest expulsionMemberRequest, Authentication authentication);
}
