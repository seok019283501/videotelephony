package org.signaling.signaling_server.domain.callroommember.mapper;

import org.signaling.signaling_server.entity.callroommember.CallRoomMemberEntity;
import org.signaling.signaling_server.entity.callroommember.enums.CallRoomMemberRole;
import org.signaling.signaling_server.entity.member.MemberEntity;

public class CallRoomMemberEntityMapper {

    public static CallRoomMemberEntity toCallRoomMemberEntity(Long memberId, Long callRoomId, CallRoomMemberRole role){
        return CallRoomMemberEntity.builder()
                .callRoomId(callRoomId)
                .memberId(memberId)
                .role(role)
                .build();
    }
}
