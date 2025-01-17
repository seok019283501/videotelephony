package org.signaling.signaling_server.domain.member.repository;

import static org.signaling.signaling_server.entity.member.QMemberEntity.memberEntity;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.entity.member.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {
    private final JpaMemberRepository jpaMemberRepository;
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Optional<MemberEntity> findById(Long memberId) {
        return jpaMemberRepository.findById(memberId);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaMemberRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaMemberRepository.existsByUsername(username);
    }

    @Override
    public void save(MemberEntity memberEntity) {
        jpaMemberRepository.save(memberEntity);
    }

    @Override
    public Optional<MemberEntity> findByUsername(String username) {
        return jpaMemberRepository.findByUsername(username);
    }

    @Override
    public void updatePasswordByEmail(String password, String email) {
        jpaQueryFactory.update(memberEntity)
                .set(memberEntity.password, password)
                .where(memberEntity.email.eq(email))
                .execute();
    }

    @Override
    public void updatePasswordById(String password, Long id) {
        jpaQueryFactory.update(memberEntity)
                .set(memberEntity.password, password)
                .where(memberEntity.id.eq(id))
                .execute();
    }

    @Override
    public Optional<MemberEntity> findByEmail(String email) {
        return jpaMemberRepository.findByEmail(email);
    }

    @Override
    public void updateNicknameByUId(Long id, String nickname) {
        jpaQueryFactory.update(memberEntity)
                .set(memberEntity.nickname, nickname)
                .where(memberEntity.id.eq(id))
                .execute();
    }


}