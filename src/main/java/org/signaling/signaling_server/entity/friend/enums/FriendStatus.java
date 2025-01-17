package org.signaling.signaling_server.entity.friend.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FriendStatus {
    REQUEST("REQUEST"),
    ACCEPT("ACCEPT")
    ;
    private final String description;
}