package org.signaling.signaling_server.domain.callroommember.repository;

import org.signaling.signaling_server.entity.callroommember.CallRoomMemberEntity;
import org.signaling.signaling_server.entity.callroommember.enums.CallRoomMemberRole;

import java.util.Optional;

public interface CallRoomMemberRepository {
    void save(CallRoomMemberEntity callRoomMemberEntity);

    //회원 유무 확인
    boolean existsByCallRoomIdAndMemberId(Long callRoomId, Long memberId);

    //회원이 방장인지 확인
    boolean existsByCallRoomIdAndMemberIdAndRole(Long callRoomId, Long memberId, CallRoomMemberRole role);

    void deleteByCallRoomIdAndMemberId(Long callRoomId, Long memberId);

    Optional<CallRoomMemberEntity> findById(Long id);


    void deleteById(Long id);
}
