package org.signaling.signaling_server.domain.friend.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "친구 정보 목록 조회 응답")
public record FriendInfoListResponse(
        @NotNull @Schema(description = "친구 정보 목록")List<FriendInfoResponse> friendList
) {
}
