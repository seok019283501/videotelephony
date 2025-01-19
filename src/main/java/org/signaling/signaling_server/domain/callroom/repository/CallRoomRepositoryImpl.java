package org.signaling.signaling_server.domain.callroom.repository;

import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.entity.callroom.CallRoomEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CallRoomRepositoryImpl implements CallRoomRepository{
    private final JpaCallRoomRepository jpaCallRoomRepository;
    @Override
    public CallRoomEntity save(CallRoomEntity callRoomEntity) {
        return jpaCallRoomRepository.save(callRoomEntity);
    }

    @Override
    public Optional<CallRoomEntity> findById(Long callRoomId) {
        return jpaCallRoomRepository.findById(callRoomId);
    }
}
