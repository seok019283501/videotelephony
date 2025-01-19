package org.signaling.signaling_server.common.type.success;

public enum CallRoomSuccessType implements SuccessTypeCode{
    CREATE_CALL_ROOM(200,"OK","통화방 생성에 성공하였습니다."),
    SEARCH_CALL_ROOM(200,"OK","통화방 목록 조회에 성공하였습니다.")
    ;

    private final Integer code;
    private final String message;
    private final String description;

    CallRoomSuccessType(Integer code, String message, String description) {
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
