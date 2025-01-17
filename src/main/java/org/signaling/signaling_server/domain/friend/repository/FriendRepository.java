package org.signaling.signaling_server.domain.friend.repository;

import org.signaling.signaling_server.entity.friend.FriendEntity;
import org.signaling.signaling_server.entity.friend.enums.FriendStatus;

public interface FriendRepository {
    void save(FriendEntity friendEntity);
    boolean existsByFromMemberIdAndToMemberIdAndStatus(Long fromMember, Long toMember, FriendStatus status);
    void updateStatusById(Long friendId, FriendStatus status);

    boolean existsByIdAndToMemberId(Long id, Long toMemberId);

    void deleteByIdAndFromMemberOrToMember(Long friendId, Long memberId);
}
