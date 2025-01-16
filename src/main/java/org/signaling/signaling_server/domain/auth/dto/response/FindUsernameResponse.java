package org.signaling.signaling_server.domain.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(description = "아이디 찾기 응답")
public record FindUsernameResponse(
        @NotBlank @Schema(description = "아이디", example = "test1234") String username
) {
}
