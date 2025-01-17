package org.signaling.signaling_server.domain.friend.mapper;


import org.signaling.signaling_server.domain.friend.dto.request.AddFriendRequest;
import org.signaling.signaling_server.entity.friend.enums.FriendStatus;
import org.signaling.signaling_server.entity.member.MemberEntity;
import org.signaling.signaling_server.kafka.dto.FriendNotification;

public class FriendResponseMapper {

    public static FriendNotification toFriendNotification(AddFriendRequest friendRequest, MemberEntity memberEntity, FriendStatus status, String message){
        return FriendNotification.builder()
                .fromMemberId(memberEntity.getId())
                .username(memberEntity.getUsername())
                .nickname(memberEntity.getNickname())
                .toMemberId(friendRequest.toMemberId())
                .status(status)
                .message(message)
                .build();
    }
}
