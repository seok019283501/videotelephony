package org.signaling.signaling_server.entity.friend;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.signaling.signaling_server.entity.friend.enums.FriendStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "friend")
public class FriendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    Long fromMemberId;
    Long toMemberId;
    @Enumerated(value = EnumType.STRING)
    FriendStatus status;
}
