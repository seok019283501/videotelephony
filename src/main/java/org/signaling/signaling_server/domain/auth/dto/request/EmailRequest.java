package org.signaling.signaling_server.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "이메일 인증을 위한 인증코드 요청")
public record EmailRequest(
        @Email @NotBlank @Schema(description = "이메일", example = "test1234@gmail.com") String email
) {
}
