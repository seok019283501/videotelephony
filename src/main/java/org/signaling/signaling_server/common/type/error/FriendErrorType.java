package org.signaling.signaling_server.common.type.error;

public enum FriendErrorType implements ErrorTypeCode{
    FRIEND_REQUEST("BAD REQUEST", "이미 친구 요청을 보냈습니다.");

    private final String message;
    private final String description;

    FriendErrorType(String message, String description) {
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
