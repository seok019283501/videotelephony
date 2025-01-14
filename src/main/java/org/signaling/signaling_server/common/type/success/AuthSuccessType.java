package org.signaling.signaling_server.common.type.success;

public enum AuthSuccessType implements SuccessTypeCode {
    SIGN_UP(201, "CREATE", "회원 가입에 성공하였습니다."),
    SIGN_IN(201, "CREATE", "로그인에 성공하였습니다.");;

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
