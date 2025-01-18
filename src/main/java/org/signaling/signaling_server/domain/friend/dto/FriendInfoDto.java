package org.signaling.signaling_server.domain.friend.dto;


import org.signaling.signaling_server.entity.friend.enums.FriendStatus;

public record FriendInfoDto(
        Long friendId,
        Long memberId,
        String nickname,
        FriendStatus friendStatus
) {

}
