package org.signaling.signaling_server.domain.callroom.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.entity.callroom.CallRoomEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.signaling.signaling_server.entity.callroom.QCallRoomEntity.callRoomEntity;
import static org.signaling.signaling_server.entity.callroommember.QCallRoomMemberEntity.callRoomMemberEntity;

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
                .select(callRoomEntity)
                .from(callRoomEntity)
                .leftJoin(callRoomMemberEntity)
                .on(callRoomEntity.id.eq(callRoomMemberEntity.callRoomId)) // call_room과 call_room_member 조인
                .where(
                        callRoomMemberEntity.memberId.eq(memberId) // member가 속한 call_room 조건
                                .and(
                                        search == null || search.isEmpty() // search 조건 추가
                                                ? null
                                                : callRoomEntity.roomName.containsIgnoreCase(search) // room_name 검색
                                )
                )
                .fetch();
    }
}
