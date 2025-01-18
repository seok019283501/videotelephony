package org.signaling.signaling_server.domain.friend.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.signaling.signaling_server.entity.friend.enums.FriendStatus;

@Builder
@Schema(description = "친구 정보 조회를 위한 응답")
public record FriendInfoResponse(
        @NotNull @Schema(description = "친구 테이블 고유 아이디", example = "1") Long friendId,
        @NotNull @Schema(description = "친구 유저 고유 아이디", example = "2") Long memberId,
        @NotBlank @Schema(description = "회원 닉네임", example = "무심천자전거길") String nickname,
        @NotBlank @Schema(description = "친구 요청 상태", example = "ACCEPT") FriendStatus friendStatus
) {
}
