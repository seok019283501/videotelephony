package org.signaling.signaling_server.common.type.success;

public enum AuthSuccessType implements SuccessTypeCode {
    SIGN_UP(201, "CREATE", "회원 가입에 성공하였습니다."),
    SIGN_IN(200, "OK", "로그인에 성공하였습니다."),
    SIGN_OUT(200, "OK", "로그아웃에 성공하였습니다."),
    REISSUE_ACCESS_TOKEN(200, "OK", "ACCESS TOKEN 재발행에 성공하였습니다."),
    EMAIL_SEND_SUCCESS(200,"OK", "이메일로 인증코드 전송에 성공하였습니다."),
    EMAIL_VERIFICATION(200, "OK", "이메일 인증코드 검증에 성공하였습니다."),
    ISSUE_PASSWORD(200,"OK","임시비밀번호 발급에 성공하였습니다.");

    private final Integer code;
    private final String message;
    private final String description;

    AuthSuccessType(Integer code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
