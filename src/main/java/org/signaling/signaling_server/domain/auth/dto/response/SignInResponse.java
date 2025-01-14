package org.signaling.signaling_server.domain.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SignInResponse(
        @NotBlank @Schema(description = "access token", example = "Bearer asdf...") String accessToken,
        @NotBlank @Schema(description = "refresh token", example = "Bearer asdf...") String refreshToken
) {
}
