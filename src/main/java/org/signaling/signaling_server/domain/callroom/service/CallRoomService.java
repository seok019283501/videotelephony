package org.signaling.signaling_server.domain.callroom.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.domain.callroom.mapper.CallRoomEntityMapper;
import org.signaling.signaling_server.domain.callroom.repository.CallRoomRepository;
import org.signaling.signaling_server.domain.callroommember.mapper.CallRoomMemberEntityMapper;
import org.signaling.signaling_server.domain.callroommember.repository.CallRoomMemberRepository;
import org.signaling.signaling_server.domain.member.dto.CustomUserDetail;
import org.signaling.signaling_server.entity.callroom.CallRoomEntity;
import org.signaling.signaling_server.entity.callroommember.CallRoomMemberEntity;
import org.signaling.signaling_server.entity.callroommember.enums.CallRoomMemberRole;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CallRoomService {
    private final CallRoomRepository callRoomRepository;

    private final CallRoomMemberRepository callRoomMemberRepository;

    //방 생성
    @Transactional
    public void createRoom(Authentication authentication) {
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();

        String roomName = userDetails.getMemberEntity().getNickname() +"님의 통화방";

        //방 생성
        CallRoomEntity callRoomEntity = CallRoomEntityMapper.toCallRoomEntity(roomName);

        CallRoomEntity newCallRoomEntity = callRoomRepository.save(callRoomEntity);

        CallRoomMemberEntity callRoomMemberEntity = CallRoomMemberEntityMapper.toCallRoomMemberEntity(userDetails.getId(), newCallRoomEntity.getId(), CallRoomMemberRole.MANAGER);

        callRoomMemberRepository.save(callRoomMemberEntity);
    }
}
