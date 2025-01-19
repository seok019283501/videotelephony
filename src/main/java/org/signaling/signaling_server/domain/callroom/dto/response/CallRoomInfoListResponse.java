package org.signaling.signaling_server.domain.callroom.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "통화방 목록 조회를 위한 요청")
public record CallRoomInfoListResponse(
        List<CallRoomInfoResponse> callRoomList
) {
}
