package org.signaling.signaling_server.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "로그인을 위한 요청")
public record SignInRequest(
        @NotBlank @Schema(description = "아이디", example = "test1234") String username,
        @NotBlank @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.") @Schema(description = "비밀번호", example = "test1234!") String password
) {
}
