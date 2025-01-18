package org.signaling.signaling_server.entity.callroommember;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.signaling.signaling_server.entity.callroommember.enums.CallRoomMemberRole;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "call_room_member")
public class CallRoomMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private Long callRoomId;
    @Enumerated(value = EnumType.STRING)
    private CallRoomMemberRole role;
}
