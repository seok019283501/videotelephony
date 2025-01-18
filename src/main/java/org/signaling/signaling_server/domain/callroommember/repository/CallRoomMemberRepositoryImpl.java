package org.signaling.signaling_server.domain.callroommember.repository;

import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.entity.callroommember.CallRoomMemberEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CallRoomMemberRepositoryImpl implements CallRoomMemberRepository{
    private final JpaCallRoomMemberRepository callRoomMemberRepository;

    @Override
    public void save(CallRoomMemberEntity callRoomMemberEntity) {
        callRoomMemberRepository.save(callRoomMemberEntity);
    }
}
