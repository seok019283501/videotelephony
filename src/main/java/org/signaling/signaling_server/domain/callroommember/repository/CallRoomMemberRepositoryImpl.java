package org.signaling.signaling_server.domain.callroommember.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.domain.callroommember.dto.CallRoomMemberInfoDto;
import org.signaling.signaling_server.entity.callroommember.CallRoomMemberEntity;
import org.signaling.signaling_server.entity.callroommember.enums.CallRoomMemberRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.signaling.signaling_server.entity.callroommember.QCallRoomMemberEntity.callRoomMemberEntity;
import static org.signaling.signaling_server.entity.member.QMemberEntity.memberEntity;

@Repository
@RequiredArgsConstructor
public class CallRoomMemberRepositoryImpl implements CallRoomMemberRepository{
    private final JpaCallRoomMemberRepository jpaCallRoomMemberRepository;
    private final JPAQueryFactory jpaQueryFactory;

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

    @Override
    public List<CallRoomMemberInfoDto> findByCallRoomId(Long callRoomId) {
        return jpaQueryFactory
                .select(
                        Projections.constructor(
                                CallRoomMemberInfoDto.class,
                                callRoomMemberEntity.id,             // call_room_member ID
                                memberEntity.nickname,              // 회원 닉네임
                                callRoomMemberEntity.role           // 회원의 역할
                        )
                )
                .from(callRoomMemberEntity)
                .leftJoin(memberEntity)
                .on(callRoomMemberEntity.memberId.eq(memberEntity.id)) // call_room_member와 member 테이블 조인
                .where(
                        callRoomMemberEntity.callRoomId.eq(callRoomId) // 특정 call_room_id 조건
                )
                .fetch(); // 결과를 리스트로 반환;
    }
}
