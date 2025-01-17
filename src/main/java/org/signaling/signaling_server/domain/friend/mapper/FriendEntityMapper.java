package org.signaling.signaling_server.domain.friend.mapper;

import org.signaling.signaling_server.domain.friend.dto.request.AddFriendRequest;
import org.signaling.signaling_server.entity.friend.FriendEntity;
import org.signaling.signaling_server.entity.friend.enums.FriendStatus;

public class FriendEntityMapper {
    public static FriendEntity toEntity(AddFriendRequest addFriendRequest, Long fromMemberId){
        return FriendEntity.builder()
                .fromMemberId(fromMemberId)
                .toMemberId(addFriendRequest.toMemberId())
                .status(FriendStatus.REQUEST)
                .build();
    }
}
