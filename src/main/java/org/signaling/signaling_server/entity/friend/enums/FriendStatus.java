package org.signaling.signaling_server.entity.friend.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FriendStatus {
        REQUEST("REQUEST"),        // 친구 요청
        ACCEPT("ACCEPT"),         // 수락
        REJECT("REJECT"),         // 거절
        CANCEL("CANCEL");         // 요청 취소
    private final String description;
}