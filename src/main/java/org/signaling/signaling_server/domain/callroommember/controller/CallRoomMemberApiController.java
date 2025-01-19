package org.signaling.signaling_server.domain.callroommember.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.common.type.success.CallRoomMemberSuccessType;
import org.signaling.signaling_server.common.type.success.CallRoomSuccessType;
import org.signaling.signaling_server.domain.callroommember.dto.request.ExpulsionMemberRequest;
import org.signaling.signaling_server.domain.callroommember.dto.request.InviteMemberIdRequest;
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

    @DeleteMapping("/expulsion")
    public Api<?> expulsionRoom(
            @Valid
            @RequestBody ExpulsionMemberRequest expulsionMemberRequest,
            Authentication authentication
    ) {
        callRoomMemberService.expulsionRoom(expulsionMemberRequest,authentication);

        return Api.success(CallRoomMemberSuccessType.EXPULSION_CALL_ROOM);
    }
}
