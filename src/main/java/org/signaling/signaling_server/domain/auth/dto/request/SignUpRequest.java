package org.signaling.signaling_server.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "회원가입을 위한 요청")
public record SignUpRequest(
        @NotBlank @Schema(description = "아이디", example = "test1234") String username,
        @NotBlank @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.") @Schema(description = "비밀번호", example = "test1234!") String password,
        @NotBlank @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.") @Schema(description = "비밀번호", example = "test1234!") String passwordConfirm,
        @NotBlank  @Schema(description = "회원이름", example = "최민석") String name,
        @NotBlank @Schema(description = "닉네임", example = "무심천자전거길") String nickname,
        @Email @NotBlank @Schema(description = "이메일", example = "exemple123@google.com") String email,
        @NotBlank @Schema(description = "생년월일", example = "2000-04-21") String birth
) {
}
