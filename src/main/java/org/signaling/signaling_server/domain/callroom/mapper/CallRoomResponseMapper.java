package org.signaling.signaling_server.domain.callroom.mapper;

import org.signaling.signaling_server.domain.callroom.dto.response.CallRoomInfoListResponse;
import org.signaling.signaling_server.domain.callroom.dto.response.CallRoomInfoResponse;
import org.signaling.signaling_server.entity.callroom.CallRoomEntity;

import java.util.List;

public class CallRoomResponseMapper {
    public static CallRoomInfoResponse toCallRoomInfoResponse(CallRoomEntity callRoomEntity){
        return CallRoomInfoResponse.builder()
                .callRoomId(callRoomEntity.getId())
                .roomName(callRoomEntity.getRoomName())
                .build();
    }

    public static CallRoomInfoListResponse toCallRoomInfoListResponse(List<CallRoomInfoResponse> callRoomInfoResponseList){
        return CallRoomInfoListResponse.builder()
                .callRoomList(callRoomInfoResponseList)
                .build();
    }
}
