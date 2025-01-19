package org.signaling.signaling_server.domain.callroommember.repository;

import org.signaling.signaling_server.entity.callroommember.CallRoomMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCallRoomMemberRepository extends JpaRepository<CallRoomMemberEntity, Long> {

    boolean existsByCallRoomIdAndMemberId(Long callRoomId, Long memberId);
}
