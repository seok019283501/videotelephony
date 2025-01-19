package org.signaling.signaling_server.domain.callroom.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.domain.callroom.dto.response.CallRoomInfoListResponse;
import org.signaling.signaling_server.domain.callroom.dto.response.CallRoomInfoResponse;
import org.signaling.signaling_server.domain.callroom.mapper.CallRoomEntityMapper;
import org.signaling.signaling_server.domain.callroom.mapper.CallRoomResponseMapper;
import org.signaling.signaling_server.domain.callroom.repository.CallRoomRepository;
import org.signaling.signaling_server.domain.callroommember.mapper.CallRoomMemberEntityMapper;
import org.signaling.signaling_server.domain.callroommember.repository.CallRoomMemberRepository;
import org.signaling.signaling_server.domain.member.dto.CustomUserDetail;
import org.signaling.signaling_server.entity.callroom.CallRoomEntity;
import org.signaling.signaling_server.entity.callroommember.CallRoomMemberEntity;
import org.signaling.signaling_server.entity.callroommember.enums.CallRoomMemberRole;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public CallRoomInfoListResponse searchRoom(String search, Authentication authentication) {
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        List<CallRoomEntity> callRoomEntityList = callRoomRepository.findBySearchAndMemberId(search, userDetails.getId());
        List<CallRoomInfoResponse> callRoomInfoResponseList = callRoomEntityList.stream().map(
                CallRoomResponseMapper::toCallRoomInfoResponse
        ).toList();

        return CallRoomResponseMapper.toCallRoomInfoListResponse(callRoomInfoResponseList);
    }
}
