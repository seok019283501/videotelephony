package org.signaling.signaling_server.domain.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(description = "토큰 재발급 응답")
public record ReissueAccessTokenResponse(
        @NotBlank @Schema(description = "access token", example = "Bearer asdf...") String accessToken
) {
}
