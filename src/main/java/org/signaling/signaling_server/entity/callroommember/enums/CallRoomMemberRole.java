package org.signaling.signaling_server.entity.callroommember.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CallRoomMemberRole {
    MANAGER("manager"),
    PARTICIPANT("participant")
    ;
    private final String description;
}
