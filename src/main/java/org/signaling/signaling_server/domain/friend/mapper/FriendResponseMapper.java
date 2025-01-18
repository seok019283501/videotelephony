package org.signaling.signaling_server.domain.friend.mapper;


import org.signaling.signaling_server.domain.friend.dto.FriendInfoDto;
import org.signaling.signaling_server.domain.friend.dto.request.AddFriendRequest;
import org.signaling.signaling_server.domain.friend.dto.response.FriendInfoListResponse;
import org.signaling.signaling_server.domain.friend.dto.response.FriendInfoResponse;
import org.signaling.signaling_server.entity.friend.FriendEntity;
import org.signaling.signaling_server.entity.friend.enums.FriendStatus;
import org.signaling.signaling_server.entity.member.MemberEntity;
import org.signaling.signaling_server.kafka.dto.FriendNotification;

import java.util.List;

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

    public static FriendInfoResponse toFriendInfoResponse(FriendInfoDto friendInfoDto){
        return FriendInfoResponse.builder()
                .friendId(friendInfoDto.friendId())
                .memberId(friendInfoDto.memberId())
                .nickname(friendInfoDto.nickname())
                .friendStatus(friendInfoDto.friendStatus())
                .build();
    }

    public static FriendInfoListResponse toFriendInfoListResponse(List<FriendInfoResponse> friendInfoResponseList){
        return FriendInfoListResponse.builder()
                .friendList(friendInfoResponseList)
                .build();
    }
}
