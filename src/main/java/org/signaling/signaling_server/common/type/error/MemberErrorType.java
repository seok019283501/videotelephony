package org.signaling.signaling_server.common.type.error;

public enum MemberErrorType implements ErrorTypeCode{
    NOT_FOUND("BAD REQUEST", "회원정보를 찾을 수 없습니다.")
    ;

    private final String message;
    private final String description;
    MemberErrorType(String message, String description) {
        this.message = message;
        this.description = description;
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
