package org.signaling.signaling_server.common.type.error;

public enum CallRoomMemberErrorType implements ErrorTypeCode{
    ALREADY_INVITE_MEMBER("BAD REQUEST", "이미 초대를 한 회원입니다."),
    INVITE_MEMBER("BAD REQUEST", "통화방에 속해 있지 않아 초대를하지 못합니다.")
    ;

    private final String message;
    private final String description;
    CallRoomMemberErrorType(String message, String description) {
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
