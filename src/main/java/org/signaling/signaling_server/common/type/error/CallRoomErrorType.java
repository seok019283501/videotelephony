package org.signaling.signaling_server.common.type.error;

public enum CallRoomErrorType implements ErrorTypeCode{
    NOT_FOUND("NOT FOUND", "통화방을 찾을 수 없습니다.")
    ;

    private final String message;
    private final String description;
    CallRoomErrorType(String message, String description) {
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
