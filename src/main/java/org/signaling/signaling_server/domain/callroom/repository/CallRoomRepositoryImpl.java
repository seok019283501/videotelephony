package org.signaling.signaling_server.domain.callroom.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.entity.callroom.CallRoomEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.signaling.signaling_server.entity.callroom.QCallRoomEntity.callRoomEntity;
import static org.signaling.signaling_server.entity.callroommember.QCallRoomMemberEntity.callRoomMemberEntity;
import static org.signaling.signaling_server.entity.member.QMemberEntity.memberEntity;

@Repository
@RequiredArgsConstructor
public class CallRoomRepositoryImpl implements CallRoomRepository{
    private final JpaCallRoomRepository jpaCallRoomRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public CallRoomEntity save(CallRoomEntity callRoomEntity) {
        return jpaCallRoomRepository.save(callRoomEntity);
    }

    @Override
    public Optional<CallRoomEntity> findById(Long callRoomId) {
        return jpaCallRoomRepository.findById(callRoomId);
    }

    @Override
    public void deleteById(Long callRoomId) {
        jpaCallRoomRepository.deleteById(callRoomId);
    }

    @Override
    public boolean existsById(Long id){
        return jpaCallRoomRepository.existsById(id);
    }

    //통화방 목록 조회
    @Override
    public List<CallRoomEntity> findBySearchAndMemberId(String search, Long memberId) {
        return jpaQueryFactory
                .selectDistinct(callRoomEntity)
                .from(callRoomEntity)
                .leftJoin(callRoomMemberEntity).on(callRoomEntity.id.eq(callRoomMemberEntity.callRoomId)) // call_room과 call_room_member 조인
                .leftJoin(memberEntity).on(callRoomMemberEntity.memberId.eq(memberEntity.id)) // call_room_member와 member 조인
                .where(
                        callRoomMemberEntity.callRoomId.in( // 요청한 회원이 속한 방 필터링
                                        JPAExpressions
                                                .select(callRoomMemberEntity.callRoomId)
                                                .from(callRoomMemberEntity)
                                                .where(callRoomMemberEntity.memberId.eq(memberId))
                                )
                                .and(
                                        search == null || search.isEmpty() // search 조건
                                                ? null // search가 없으면 조건 무시
                                                : callRoomEntity.roomName.containsIgnoreCase(search) // roomName 검색
                                                .or(memberEntity.nickname.containsIgnoreCase(search)) // member.nickname 검색
                                )
                )
                .fetch();
    }
}
