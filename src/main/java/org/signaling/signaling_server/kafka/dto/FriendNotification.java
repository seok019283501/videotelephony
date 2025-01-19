package org.signaling.signaling_server.kafka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.signaling.signaling_server.entity.friend.enums.FriendStatus;

@Builder
public record FriendNotification(
        FriendStatus status,
        @JsonProperty("from_member_id") Long fromMemberId,
        @JsonProperty("to_member_id")  Long toMemberId,
         String username,
         String nickname,
         String message
)
{

}