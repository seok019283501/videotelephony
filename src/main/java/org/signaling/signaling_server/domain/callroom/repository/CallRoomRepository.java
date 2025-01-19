package org.signaling.signaling_server.domain.callroom.repository;

import org.signaling.signaling_server.entity.callroom.CallRoomEntity;

import java.util.List;
import java.util.Optional;

public interface CallRoomRepository {
    CallRoomEntity save(CallRoomEntity callRoomEntity);

    Optional<CallRoomEntity> findById(Long callRoomId);

    void deleteById(Long callRoomId);

    boolean existsById(Long id);

    List<CallRoomEntity> findBySearchAndMemberId(String search, Long memberId);
}
