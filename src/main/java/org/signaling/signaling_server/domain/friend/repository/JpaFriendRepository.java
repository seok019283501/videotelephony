package org.signaling.signaling_server.domain.friend.repository;

import org.signaling.signaling_server.entity.friend.FriendEntity;
import org.signaling.signaling_server.entity.friend.enums.FriendStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFriendRepository extends JpaRepository<FriendEntity, Long> {
    boolean existsByFromMemberIdAndToMemberIdAndStatus(Long fromMember, Long toMember, FriendStatus status);

    boolean existsByIdAndToMemberId(Long id, Long toMemberId);
}
