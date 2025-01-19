package org.signaling.signaling_server.domain.callroommember.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.common.type.success.CallRoomMemberSuccessType;
import org.signaling.signaling_server.common.type.success.CallRoomSuccessType;
import org.signaling.signaling_server.domain.callroommember.dto.request.ExitMemberRequest;
import org.signaling.signaling_server.domain.callroommember.dto.request.ExpulsionMemberRequest;
import org.signaling.signaling_server.domain.callroommember.dto.request.InviteMemberIdRequest;
import org.signaling.signaling_server.domain.callroommember.dto.response.CallRoomMemberInfoListResponse;
import org.signaling.signaling_server.domain.callroommember.service.CallRoomMemberService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/call-room/member")
public class CallRoomMemberApiController implements CallRoomMemberApi{
    private final CallRoomMemberService callRoomMemberService;
    //방 초대
    @PostMapping("/invite")
    public Api<?> inviteRoom(
            @Valid
            @RequestBody InviteMemberIdRequest inviteMemberIdRequest,
            Authentication authentication
    ){
        callRoomMemberService.inviteRoom(inviteMemberIdRequest, authentication);
        return Api.success(CallRoomMemberSuccessType.INVITE_CALL_ROOM);
    }

    //방 퇴출
    @DeleteMapping("/expulsion")
    public Api<?> expulsionRoom(
            @Valid
            @RequestBody ExpulsionMemberRequest expulsionMemberRequest,
            Authentication authentication
    ) {
        callRoomMemberService.expulsionRoom(expulsionMemberRequest,authentication);

        return Api.success(CallRoomMemberSuccessType.EXPULSION_CALL_ROOM);
    }

    //방 퇴장
    @DeleteMapping("/exit")
    public Api<?> exitRoom(
            @Valid
            @RequestBody ExitMemberRequest exitMemberRequest,
            Authentication authentication
    ){
        callRoomMemberService.exitRoom(exitMemberRequest, authentication);
        return Api.success(CallRoomMemberSuccessType.EXIT_CALL_ROOM);
    }

    @GetMapping("/{callRoomId}")
    public Api<CallRoomMemberInfoListResponse> searchCallRoomMember(
            @PathVariable("callRoomId") Long callRoomId,
            Authentication authentication
    ) {
        CallRoomMemberInfoListResponse callRoomMemberInfoListResponse = callRoomMemberService.searchCallRoomMember(callRoomId, authentication);
        return Api.success(CallRoomMemberSuccessType.SEARCH_CALL_ROOM_MEMBER,callRoomMemberInfoListResponse);
    }
}
