package org.signaling.signaling_server.domain.callroom.controller;

import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.common.type.success.CallRoomSuccessType;
import org.signaling.signaling_server.domain.callroom.service.CallRoomService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/call-room")
public class CallRoomApiController implements CallRoomApi {
    private final CallRoomService callRoomService;

    //방 생성
    @PostMapping
    public Api<?> createRoom(Authentication authentication) {
        callRoomService.createRoom(authentication);
        return Api.success(CallRoomSuccessType.CREATE_CALL_ROOM);
    }
}
