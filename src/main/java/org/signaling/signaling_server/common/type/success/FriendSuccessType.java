package org.signaling.signaling_server.common.type.success;

public enum FriendSuccessType implements SuccessTypeCode {
    ADD_FRIEND(200, "OK", "친구추가 요청을 성공하였습니다"),
    ACCEPT_ADD_FRIEND(200, "OK", "친구추가 허가 요청을 성공하였습니다"),
    DELETE_FRIEND(200, "OK", "친구 삭제 요청을 성공하였습니다"),
    SEARCH_FRIEND(200, "OK", "친구 검색 및 목록 조회 요청을 성공하였습니다.")
    ;
    private final Integer code;
    private final String message;
    private final String description;

    FriendSuccessType(Integer code, String message, String description) {
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
