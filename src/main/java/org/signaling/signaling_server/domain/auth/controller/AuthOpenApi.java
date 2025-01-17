package org.signaling.signaling_server.domain.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.domain.auth.dto.request.EmailRequest;
import org.signaling.signaling_server.domain.auth.dto.request.SignInRequest;
import org.signaling.signaling_server.domain.auth.dto.request.SignUpRequest;
import org.signaling.signaling_server.domain.auth.dto.request.VerificationCodeRequest;
import org.signaling.signaling_server.domain.auth.dto.response.FindUsernameResponse;
import org.signaling.signaling_server.domain.auth.dto.response.SignInResponse;

@Tag(name = "Auth Open Api", description = "인증 관련 Open API 목록입니다.")
public interface AuthOpenApi {
    @Operation(summary = "회원가입을 합니다.", description = "담당자: 최민석")
    Api<?> signUp(SignUpRequest signUpRequest);

    @Operation(summary = "로그인을 합니다.", description = "담당자: 최민석")
    Api<SignInResponse> signIn(SignInRequest signInRequest);
    @Operation(summary = "이메일 인증코드를 발송합니다.", description = "담당자: 최민석")
    Api<?> sendCode(EmailRequest emailRequest);
    @Operation(summary = "이메일 인증코드를 발송합니다.", description = "담당자: 최민석")
    Api<?> checkVerification(VerificationCodeRequest verificationCodeRequest);
    @Operation(summary = "임시 비밀번호를 발송합니다.", description = "담당자: 최민석")
    Api<?> issuePassword(EmailRequest emailRequest);

    @Operation(summary = "아이디 찾기를 합니다.", description = "담당자: 최민석")
    Api<FindUsernameResponse> findUsername(EmailRequest emailRequest);

}
