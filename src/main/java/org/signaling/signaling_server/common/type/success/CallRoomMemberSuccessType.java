package org.signaling.signaling_server.common.type.success;

public enum CallRoomMemberSuccessType implements SuccessTypeCode {
    INVITE_CALL_ROOM(200,"OK","통화방 초대에 성공하였습니다.")
    ;


    private final Integer code;
    private final String message;
    private final String description;

    CallRoomMemberSuccessType(Integer code, String message, String description) {
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
