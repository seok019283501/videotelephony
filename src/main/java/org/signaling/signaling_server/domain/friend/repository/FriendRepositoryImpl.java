package org.signaling.signaling_server.domain.friend.repository;

import static org.signaling.signaling_server.entity.friend.QFriendEntity.friendEntity;
import static org.signaling.signaling_server.entity.member.QMemberEntity.memberEntity;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.domain.friend.dto.FriendInfoDto;
import org.signaling.signaling_server.entity.friend.FriendEntity;
import org.signaling.signaling_server.entity.friend.enums.FriendStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<FriendInfoDto> findByMemberId(String nickname, Long memberId) {
        return jpaQueryFactory
                .select(
                        Projections.constructor(
                                FriendInfoDto.class,
                                friendEntity.id, // friendId
                                friendEntity.fromMemberId
                                        .when(memberId).then(friendEntity.toMemberId) // b가 from이면 to를 가져옴
                                        .otherwise(friendEntity.fromMemberId),       // b가 to이면 from을 가져옴
                                memberEntity.nickname,                          // 해당 memberId의 nickname 가져오기
                                friendEntity.status                             // friendStatus 추가
                        )
                )
                .from(friendEntity)
                .leftJoin(memberEntity) // member 테이블과 조인
                .on(
                        friendEntity.fromMemberId.when(memberId).then(friendEntity.toMemberId)
                                .otherwise(friendEntity.fromMemberId).eq(memberEntity.id)
                )
                .where(
                        friendEntity.fromMemberId.eq(memberId)
                                .or(friendEntity.toMemberId.eq(memberId))
                                .and(
                                        nickname == null || nickname.isEmpty() // nickname 조건 추가
                                                ? null // 빈 문자열이면 조건 무시
                                                : memberEntity.nickname.contains(nickname) // 특정 문자열 포함
                                )
                )
                .orderBy(
                        friendEntity.status.asc(), // REQUEST를 위로 올림 (REQUEST < ACCEPTED)
                        friendEntity.id.asc()       // 같은 상태에서는 friendId 기준 정렬
                )
                .fetch();
    }
}
