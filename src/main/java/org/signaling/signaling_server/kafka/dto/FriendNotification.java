package org.signaling.signaling_server.kafka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.signaling.signaling_server.entity.friend.enums.FriendStatus;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FriendNotification {
    private FriendStatus status;
    @JsonProperty("from_member_id")
    private Long fromMemberId;
    @JsonProperty("to_member_id")
    private Long toMemberId;
    private String username;
    private String nickname;
    private String message;
}