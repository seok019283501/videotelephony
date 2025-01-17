package org.signaling.signaling_server.domain.friend.repository;

import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.entity.friend.FriendEntity;
import org.signaling.signaling_server.entity.friend.enums.FriendStatus;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FriendRepositoryImpl implements FriendRepository {
    private final JpaFriendRepository jpaFriendRepository;

    @Override
    public void save(FriendEntity friendEntity) {
        jpaFriendRepository.save(friendEntity);
    }

    @Override
    public boolean existsByFromMemberIdAndToMemberIdAndStatus(Long fromMember, Long toMember, FriendStatus status) {
        return jpaFriendRepository.existsByFromMemberIdAndToMemberIdAndStatus(fromMember,toMember, status);
    }
}
