package org.signaling.signaling_server.domain.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MemberInfoResponse(
        @NotBlank @Schema(description = "아이디", example = "test1234") String username,
        @NotBlank  @Schema(description = "회원이름", example = "최민석") String name,
        @NotBlank @Schema(description = "닉네임", example = "무심천자전거길") String nickname,
        @Email @NotBlank @Schema(description = "이메일", example = "exemple123@google.com") String email,
        @NotBlank @Schema(description = "생년월일", example = "2000-04-21") String birth,
        @NotBlank@Schema(description = "생성일자", example = "2025-01-16T00:28:36")String createdAt
) {
}
