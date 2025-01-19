package org.signaling.signaling_server.domain.callroommember.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "통화방 참여자 정보 목록을 위한 요청")
public record CallRoomMemberInfoListResponse(
        List<CallRoomMemberInfoResponse> callRoomMemberList
) {
}
