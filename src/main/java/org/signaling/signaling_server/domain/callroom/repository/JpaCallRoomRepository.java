package org.signaling.signaling_server.domain.callroom.repository;

import org.signaling.signaling_server.entity.callroom.CallRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCallRoomRepository extends JpaRepository<CallRoomEntity, Long> {
}
