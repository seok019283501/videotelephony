package org.signaling.signaling_server.common.type.error;

public enum CallRoomMemberErrorType implements ErrorTypeCode{
    ALREADY_INVITE_MEMBER("BAD REQUEST", "이미 초대를 한 회원입니다."),
    INVITE_MEMBER("BAD REQUEST", "통화방에 속해 있지 않아 초대를하지 못합니다."),
    MANAGER_MEMBER("BAD REQUEST", "회원 퇴출 권한이 없습니다."),
    NOT_FOUND("NOT FOUND", "통화방 회원을 찾을 수 없습니다."),
    NOT_SELF_EXPULSION("BAD REQUEST", "자기 자신을 퇴출할 수 없습니다."),
    SELF_EXIT("BAD REQUEST", "자기 자신이 퇴장할 수 있습니다.")
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
