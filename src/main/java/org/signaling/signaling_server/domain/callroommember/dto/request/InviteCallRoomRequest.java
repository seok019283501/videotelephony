package org.signaling.signaling_server.domain.callroommember.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "통화방 초대를 위한 요청")
public record InviteCallRoomRequest(
        @NotNull @Schema(description = "통화방 고유 아이디", example = "1") Long callRoomId,
        @NotNull @Schema(description = "초대할 회원의 고유 아이디", example = "3") Long memberId

) {
}
