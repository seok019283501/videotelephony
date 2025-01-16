package org.signaling.signaling_server.common.type.success;

public enum MemberSuccessType implements SuccessTypeCode{
    MEMBER_INFO(200, "OK", "회원정보 조회를 성공하였습니다.");
    private final Integer code;
    private final String message;
    private final String description;

    MemberSuccessType(Integer code, String message, String description) {
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
