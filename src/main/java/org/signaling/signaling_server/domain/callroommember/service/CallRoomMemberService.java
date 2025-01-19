package org.signaling.signaling_server.domain.callroommember.service;

import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.common.exception.BadRequestException;
import org.signaling.signaling_server.common.exception.NotFoundException;
import org.signaling.signaling_server.common.type.error.CallRoomErrorType;
import org.signaling.signaling_server.common.type.error.CallRoomMemberErrorType;
import org.signaling.signaling_server.common.type.error.MemberErrorType;
import org.signaling.signaling_server.domain.callroommember.dto.request.InviteCallRoomRequest;
import org.signaling.signaling_server.domain.callroom.repository.CallRoomRepository;
import org.signaling.signaling_server.domain.callroommember.mapper.CallRoomMemberEntityMapper;
import org.signaling.signaling_server.domain.callroommember.mapper.CallRoomMemberResponseMapper;
import org.signaling.signaling_server.domain.callroommember.repository.CallRoomMemberRepository;
import org.signaling.signaling_server.domain.member.dto.CustomUserDetail;
import org.signaling.signaling_server.domain.member.repository.MemberRepository;
import org.signaling.signaling_server.entity.callroom.CallRoomEntity;
import org.signaling.signaling_server.entity.callroommember.CallRoomMemberEntity;
import org.signaling.signaling_server.entity.callroommember.enums.CallRoomMemberRole;
import org.signaling.signaling_server.entity.member.MemberEntity;
import org.signaling.signaling_server.kafka.KafkaProducerService;
import org.signaling.signaling_server.kafka.dto.CallRoomNotification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CallRoomMemberService {
    private final CallRoomMemberRepository callRoomMemberRepository;
    private final MemberRepository memberRepository;
    private final KafkaProducerService kafkaProducerService;
    private final CallRoomRepository callRoomRepository;
    public void inviteRoom(InviteCallRoomRequest inviteCallRoomRequest, Authentication authentication) {

        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();

        CallRoomEntity callRoomEntity = callRoomRepository.findById(inviteCallRoomRequest.callRoomId())
                .orElseThrow(()->new NotFoundException(CallRoomErrorType.NOT_FOUND));

        //초대 권한이 있는지 확인
        if(!callRoomMemberRepository.existsByCallRoomIdAndMemberId(inviteCallRoomRequest.callRoomId(), userDetail.getId())){
            throw new BadRequestException(CallRoomMemberErrorType.INVITE_MEMBER);
        }

        //이미 존재하는 회원인지 확인
        if(callRoomMemberRepository.existsByCallRoomIdAndMemberId(inviteCallRoomRequest.callRoomId(), inviteCallRoomRequest.memberId())){
            throw new BadRequestException(CallRoomMemberErrorType.ALREADY_INVITE_MEMBER);
        }

        MemberEntity memberEntity = memberRepository.findById(inviteCallRoomRequest.memberId())
                .orElseThrow(()-> new NotFoundException(MemberErrorType.NOT_FOUND));

        //회원 초대
        CallRoomMemberEntity callRoomMemberEntity =
                CallRoomMemberEntityMapper.toCallRoomMemberEntity(memberEntity.getId(), inviteCallRoomRequest.callRoomId(), CallRoomMemberRole.PARTICIPANT);

        callRoomMemberRepository.save(callRoomMemberEntity);

        //초대 알림
        String message = callRoomEntity.getRoomName() + "에 초대되었습니다.";

        CallRoomNotification callRoomNotification = CallRoomMemberResponseMapper.toCallRoomNotification(memberEntity, inviteCallRoomRequest.callRoomId(), message);

        kafkaProducerService.sendCallRoomNotification(callRoomNotification);

    }
}
