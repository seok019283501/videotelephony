package org.signaling.signaling_server.domain.callroom.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@Schema(description = "통화방 정보를 위한 요청")
public record CallRoomInfoResponse(
        @NotNull @Schema(description = "통화방 고유 아이디", example = "1") Long callRoomId,
        @NotBlank @Schema(description = "통화방 이름", example = "홍길동의 통화방") String roomName
) {
}
