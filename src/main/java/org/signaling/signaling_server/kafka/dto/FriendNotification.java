package org.signaling.signaling_server.kafka.dto;

import lombok.*;
import org.signaling.signaling_server.entity.friend.enums.FriendStatus;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FriendNotification {
    private FriendStatus status;
    private Long fromMemberId;
    private Long toMemberId;
    private String username;
    private String nickname;
    private String message;

}