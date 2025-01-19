package org.signaling.signaling_server.kafka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.signaling.signaling_server.entity.friend.enums.FriendStatus;

@Builder
public record CallRoomNotification(
       @JsonProperty("call_room_id")  Long callRoomId,
       @JsonProperty("member_id")  Long memberId,
       String username,
       String nickname,
       String message
) {
}
