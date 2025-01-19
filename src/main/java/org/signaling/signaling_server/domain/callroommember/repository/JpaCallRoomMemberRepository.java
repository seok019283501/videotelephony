package org.signaling.signaling_server.domain.callroommember.repository;

import org.signaling.signaling_server.entity.callroommember.CallRoomMemberEntity;
import org.signaling.signaling_server.entity.callroommember.enums.CallRoomMemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaCallRoomMemberRepository extends JpaRepository<CallRoomMemberEntity, Long> {

    boolean existsByCallRoomIdAndMemberId(Long callRoomId, Long memberId);

    boolean existsByCallRoomIdAndMemberIdAndRole(Long callRoomId, Long memberId, CallRoomMemberRole role);

    void deleteByCallRoomIdAndMemberId(Long callRoomId, Long memberId);

    List<CallRoomMemberEntity> findByCallRoomId(Long callRoomId);
}
