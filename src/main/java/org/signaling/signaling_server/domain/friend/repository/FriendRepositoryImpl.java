package org.signaling.signaling_server.domain.friend.repository;

import static org.signaling.signaling_server.entity.friend.QFriendEntity.friendEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.entity.friend.FriendEntity;
import org.signaling.signaling_server.entity.friend.enums.FriendStatus;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FriendRepositoryImpl implements FriendRepository {
    private final JpaFriendRepository jpaFriendRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void save(FriendEntity friendEntity) {
        jpaFriendRepository.save(friendEntity);
    }

    @Override
    public boolean existsByFromMemberIdAndToMemberIdAndStatus(Long fromMember, Long toMember, FriendStatus status) {
        return jpaFriendRepository.existsByFromMemberIdAndToMemberIdAndStatus(fromMember,toMember, status);
    }

    @Override
    public void updateStatusById(Long friendId, FriendStatus status) {
        jpaQueryFactory.update(friendEntity)
                .set(friendEntity.status, status)
                .where(friendEntity.id.eq(friendId))
                .execute();
    }

    @Override
    public boolean existsByIdAndToMemberId(Long id, Long toMemberId) {
        return jpaFriendRepository.existsByIdAndToMemberId(id, toMemberId);
    }

    @Override
    public void deleteByIdAndFromMemberOrToMember(Long friendId, Long memberId) {
        jpaQueryFactory.delete(friendEntity)
                .where(
                        friendEntity.id.eq(friendId)
                                .and(
                                        friendEntity.fromMemberId.eq(memberId)
                                                .or(friendEntity.toMemberId.eq(memberId))
                                )
                )
                .execute();
    }
}
