package org.signaling.signaling_server.domain.callroommember.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.signaling.signaling_server.entity.callroommember.enums.CallRoomMemberRole;

@Builder
@Schema(description = "통화방 참여자 정보를 위한 요청")
public record CallRoomMemberInfoResponse(
        @NotNull @Schema(description = "통화방 참여자 고유 아이디", example = "1") Long callRoomMemberId,
        @NotBlank @Schema(description = "통화방 참여자 닉네임", example = "홍길동") String nickname,
        @NotBlank @Schema(description = "통화방 참여자 역할", example = "MANAGER") CallRoomMemberRole role
) {
}
