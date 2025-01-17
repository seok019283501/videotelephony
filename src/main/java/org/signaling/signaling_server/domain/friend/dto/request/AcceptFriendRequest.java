package org.signaling.signaling_server.domain.friend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "친구추가 허가를 위한 요청")
public record AcceptFriendRequest(
        @NotNull @Schema(description = "친구 유저 고유 아이디", example = "1") Long friendId
) {
}
