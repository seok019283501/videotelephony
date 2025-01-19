package org.signaling.signaling_server.domain.callroommember.mapper;

import org.signaling.signaling_server.entity.member.MemberEntity;
import org.signaling.signaling_server.kafka.dto.CallRoomNotification;

public class CallRoomMemberResponseMapper {
    public static CallRoomNotification toCallRoomNotification(MemberEntity memberEntity, Long callRoomId, String message){
        return CallRoomNotification.builder()
                .callRoomId(callRoomId)
                .memberId(memberEntity.getId())
                .nickname(memberEntity.getNickname())
                .username(memberEntity.getUsername())
                .message(message)
                .build();
    }
}
