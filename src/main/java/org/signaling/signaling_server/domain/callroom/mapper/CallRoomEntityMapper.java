package org.signaling.signaling_server.domain.callroom.mapper;

import org.signaling.signaling_server.entity.callroom.CallRoomEntity;

import java.time.LocalDateTime;

public class CallRoomEntityMapper {
    public static CallRoomEntity toCallRoomEntity(String roomName){
        return CallRoomEntity.builder()
                .roomName(roomName)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
