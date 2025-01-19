package org.signaling.signaling_server.domain.callroommember.mapper;

import org.signaling.signaling_server.domain.callroommember.dto.CallRoomMemberInfoDto;
import org.signaling.signaling_server.domain.callroommember.dto.response.CallRoomMemberInfoListResponse;
import org.signaling.signaling_server.domain.callroommember.dto.response.CallRoomMemberInfoResponse;
import org.signaling.signaling_server.entity.member.MemberEntity;
import org.signaling.signaling_server.kafka.dto.CallRoomNotification;

import java.util.List;

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

    public static CallRoomMemberInfoResponse toCallRoomMemberInfoResponse(CallRoomMemberInfoDto callRoomMemberInfoDto){
        return CallRoomMemberInfoResponse.builder()
                .callRoomMemberId(callRoomMemberInfoDto.id())
                .nickname(callRoomMemberInfoDto.nickname())
                .role(callRoomMemberInfoDto.role())
                .build();
    }

    public static CallRoomMemberInfoListResponse toCallRoomMemberInfoListResponse(List<CallRoomMemberInfoResponse> callRoomMemberInfoResponseList){
        return CallRoomMemberInfoListResponse.builder()
                .callRoomMemberList(callRoomMemberInfoResponseList)
                .build();
    }
}
