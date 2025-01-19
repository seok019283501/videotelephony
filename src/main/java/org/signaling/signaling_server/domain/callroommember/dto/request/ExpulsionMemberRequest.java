package org.signaling.signaling_server.domain.callroommember.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record ExpulsionMemberRequest(
        @NotNull @Schema(description = "통화방 고유 아이디", example = "1") Long callRoomId,
        @NotNull @Schema(description = "통화방 참여 회원 고유 아이디", example = "1") Long callRoomMemberId
) {
}
