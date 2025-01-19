package org.signaling.signaling_server.domain.callroom.repository;

import org.signaling.signaling_server.entity.callroom.CallRoomEntity;

import java.util.Optional;

public interface CallRoomRepository {
    CallRoomEntity save(CallRoomEntity callRoomEntity);

    Optional<CallRoomEntity> findById(Long callRoomId);

    void deleteById(Long callRoomMemberId);

    boolean existsById(Long id);
}
