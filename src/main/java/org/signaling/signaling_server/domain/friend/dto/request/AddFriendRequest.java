package org.signaling.signaling_server.domain.friend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "비밀번호 변경을 위한 요청")
public record AddFriendRequest(
        @NotNull @Schema(description = "친구 유저 고유 아이디", example = "1") Long toMemberId
) {
}
