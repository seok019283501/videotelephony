package org.signaling.signaling_server.domain.callroommember.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.common.exception.BadRequestException;
import org.signaling.signaling_server.common.exception.NotFoundException;
import org.signaling.signaling_server.common.type.error.CallRoomErrorType;
import org.signaling.signaling_server.common.type.error.CallRoomMemberErrorType;
import org.signaling.signaling_server.common.type.error.MemberErrorType;
import org.signaling.signaling_server.domain.callroommember.dto.CallRoomMemberInfoDto;
import org.signaling.signaling_server.domain.callroommember.dto.request.ExitMemberRequest;
import org.signaling.signaling_server.domain.callroommember.dto.request.ExpulsionMemberRequest;
import org.signaling.signaling_server.domain.callroommember.dto.request.InviteMemberIdRequest;
import org.signaling.signaling_server.domain.callroom.repository.CallRoomRepository;
import org.signaling.signaling_server.domain.callroommember.dto.response.CallRoomMemberInfoListResponse;
import org.signaling.signaling_server.domain.callroommember.dto.response.CallRoomMemberInfoResponse;
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

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CallRoomMemberService {
    private final CallRoomMemberRepository callRoomMemberRepository;
    private final MemberRepository memberRepository;
    private final KafkaProducerService kafkaProducerService;
    private final CallRoomRepository callRoomRepository;
    public void inviteRoom(InviteMemberIdRequest inviteMemberIdRequest, Authentication authentication) {

        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();

        CallRoomEntity callRoomEntity = callRoomRepository.findById(inviteMemberIdRequest.callRoomId())
                .orElseThrow(()->new NotFoundException(CallRoomErrorType.NOT_FOUND));

        //초대 권한이 있는지 확인
        if(!callRoomMemberRepository.existsByCallRoomIdAndMemberId(inviteMemberIdRequest.callRoomId(), userDetail.getId())){
            throw new BadRequestException(CallRoomMemberErrorType.INVITE_MEMBER);
        }

        //이미 존재하는 회원인지 확인
        if(callRoomMemberRepository.existsByCallRoomIdAndMemberId(inviteMemberIdRequest.callRoomId(), inviteMemberIdRequest.memberId())){
            throw new BadRequestException(CallRoomMemberErrorType.ALREADY_INVITE_MEMBER);
        }

        MemberEntity memberEntity = memberRepository.findById(inviteMemberIdRequest.memberId())
                .orElseThrow(()-> new NotFoundException(MemberErrorType.NOT_FOUND));

        //회원 초대
        CallRoomMemberEntity callRoomMemberEntity =
                CallRoomMemberEntityMapper.toCallRoomMemberEntity(memberEntity.getId(), inviteMemberIdRequest.callRoomId(), CallRoomMemberRole.PARTICIPANT);

        callRoomMemberRepository.save(callRoomMemberEntity);

        //초대 알림
        String message = callRoomEntity.getRoomName() + "에 초대되었습니다.";

        CallRoomNotification callRoomNotification = CallRoomMemberResponseMapper.toCallRoomNotification(memberEntity, inviteMemberIdRequest.callRoomId(), message);

        kafkaProducerService.sendCallRoomNotification(callRoomNotification);

    }

    public void expulsionRoom(ExpulsionMemberRequest expulsionMemberRequest, Authentication authentication) {
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();

        if(callRoomRepository.existsById(expulsionMemberRequest.callRoomId())){
            throw new NotFoundException(CallRoomErrorType.NOT_FOUND);
        }

        //회원 퇴출 권한이 있는지 확인
        if(!callRoomMemberRepository.existsByCallRoomIdAndMemberIdAndRole(expulsionMemberRequest.callRoomId(), userDetail.getId(), CallRoomMemberRole.MANAGER)){
            throw new BadRequestException(CallRoomMemberErrorType.MANAGER_MEMBER);
        }

        CallRoomMemberEntity callRoomMemberEntity = callRoomMemberRepository.findById(expulsionMemberRequest.callRoomMemberId())
                .orElseThrow(()->new NotFoundException(CallRoomMemberErrorType.NOT_FOUND));

        //자신을 퇴출하는지 확인
        if(Objects.equals(callRoomMemberEntity.getMemberId(), userDetail.getId())){
            throw new BadRequestException(CallRoomMemberErrorType.NOT_SELF_EXPULSION);
        }

        callRoomMemberRepository.deleteById(expulsionMemberRequest.callRoomMemberId());
    }

    @Transactional
    public void exitRoom(ExitMemberRequest exitMemberRequest, Authentication authentication) {
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();

        if(!callRoomRepository.existsById(exitMemberRequest.callRoomId())){
            throw new NotFoundException(CallRoomErrorType.NOT_FOUND);
        }

        //방장일 경우 통화방 삭제
        if(callRoomMemberRepository.existsByCallRoomIdAndMemberIdAndRole(exitMemberRequest.callRoomId(), userDetail.getId(), CallRoomMemberRole.MANAGER)){
            callRoomRepository.deleteById(exitMemberRequest.callRoomId());
            return;
        }

        //일반 회원일 경우
        callRoomMemberRepository.deleteByCallRoomIdAndMemberId(exitMemberRequest.callRoomId(), userDetail.getId());

    }

    public CallRoomMemberInfoListResponse searchCallRoomMember(Long callRoomId, Authentication authentication) {
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();

        //통화방이 있는지 확인
        if(!callRoomRepository.existsById(callRoomId)){
            throw new NotFoundException(CallRoomErrorType.NOT_FOUND);
        }

        //통화방 확인할 수 있는지 확인
        if(!callRoomMemberRepository.existsByCallRoomIdAndMemberId(callRoomId, userDetail.getId())){
            throw new NotFoundException(CallRoomMemberErrorType.NOT_FOUND);
        }

        List<CallRoomMemberInfoDto> callRoomMemberInfoDtoList = callRoomMemberRepository.findByCallRoomId(callRoomId);

        List<CallRoomMemberInfoResponse> callRoomMemberInfoResponseList = callRoomMemberInfoDtoList.stream().map(
                CallRoomMemberResponseMapper::toCallRoomMemberInfoResponse
        ).toList();

        return CallRoomMemberResponseMapper.toCallRoomMemberInfoListResponse(callRoomMemberInfoResponseList);
    }
}
