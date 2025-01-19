package org.signaling.signaling_server.domain.callroommember.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.common.type.success.CallRoomMemberSuccessType;
import org.signaling.signaling_server.domain.callroommember.dto.request.InviteCallRoomRequest;
import org.signaling.signaling_server.domain.callroommember.service.CallRoomMemberService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/call-room/member")
public class CallRoomMemberApiController implements CallRoomMemberApi{
    private final CallRoomMemberService callRoomMemberService;
    //방 초대
    @PostMapping("/invite")
    public Api<?> inviteRoom(
            @Valid
            @RequestBody InviteCallRoomRequest inviteCallRoomRequest,
            Authentication authentication
    ){
        callRoomMemberService.inviteRoom(inviteCallRoomRequest, authentication);
        return Api.success(CallRoomMemberSuccessType.INVITE_CALL_ROOM);
    }
}
