package org.signaling.signaling_server.domain.callroom.controller;

import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.common.type.success.CallRoomSuccessType;
import org.signaling.signaling_server.domain.callroom.dto.response.CallRoomInfoListResponse;
import org.signaling.signaling_server.domain.callroom.service.CallRoomService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public Api<CallRoomInfoListResponse> searchRoom(
            @RequestParam String search,
            Authentication authentication
    ) {
        CallRoomInfoListResponse callRoomInfoListResponse = callRoomService.searchRoom(search, authentication);
        return Api.success(CallRoomSuccessType.SEARCH_CALL_ROOM, callRoomInfoListResponse);
    }
}
