package org.signaling.signaling_server.domain.callroom.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.signaling.signaling_server.common.api.Api;
import org.springframework.security.core.Authentication;

@Tag(name = "CallRoom Api", description = "통화방 관련 API 목록입니다.")
public interface CallRoomApi {
    @Operation(summary = "통화방 생성을 합니다.", description = "담당자: 최민석")
    Api<?> createRoom(Authentication authentication);
}
