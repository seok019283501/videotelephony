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
}