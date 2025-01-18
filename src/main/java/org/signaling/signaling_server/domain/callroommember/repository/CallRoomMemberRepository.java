package org.signaling.signaling_server.domain.callroommember.repository;

import org.signaling.signaling_server.entity.callroommember.CallRoomMemberEntity;

public interface CallRoomMemberRepository {
    void save(CallRoomMemberEntity callRoomMemberEntity);
}
