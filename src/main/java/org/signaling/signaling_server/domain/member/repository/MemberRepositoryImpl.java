package org.signaling.signaling_server.domain.member.repository;

import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.entity.member.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {
    private final JpaMemberRepository jpaMemberRepository;

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


}