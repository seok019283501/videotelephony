package org.signaling.signaling_server.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "닉네임 변경을 위한 요청")
public record ChangeNicknameRequest(
        @NotBlank @Schema(description = "닉네임", example = "무심천자전거길") String nickname
) {
}
