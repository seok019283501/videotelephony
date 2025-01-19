package org.signaling.signaling_server.domain.callroommember.repository;

import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.entity.callroommember.CallRoomMemberEntity;
import org.signaling.signaling_server.entity.callroommember.enums.CallRoomMemberRole;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CallRoomMemberRepositoryImpl implements CallRoomMemberRepository{
    private final JpaCallRoomMemberRepository jpaCallRoomMemberRepository;

    @Override
    public void save(CallRoomMemberEntity callRoomMemberEntity) {
        jpaCallRoomMemberRepository.save(callRoomMemberEntity);
    }

    @Override
    public boolean existsByCallRoomIdAndMemberId(Long callRoomId, Long memberId) {
        return jpaCallRoomMemberRepository.existsByCallRoomIdAndMemberId(callRoomId,memberId);
    }

    @Override
    public boolean existsByCallRoomIdAndMemberIdAndRole(Long callRoomId, Long memberId, CallRoomMemberRole role) {
        return jpaCallRoomMemberRepository.existsByCallRoomIdAndMemberIdAndRole(callRoomId, memberId, role);
    }

    @Override
    public void deleteByCallRoomIdAndMemberId(Long callRoomId, Long memberId) {
        jpaCallRoomMemberRepository.deleteByCallRoomIdAndMemberId(callRoomId, memberId);
    }

    @Override
    public Optional<CallRoomMemberEntity> findById(Long id) {
        return jpaCallRoomMemberRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        jpaCallRoomMemberRepository.deleteById(id);
    }
}
