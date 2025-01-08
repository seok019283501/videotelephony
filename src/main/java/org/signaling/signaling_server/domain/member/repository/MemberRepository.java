package org.signaling.signaling_server.domain.member.repository;

import org.signaling.signaling_server.entity.member.MemberEntity;

import java.util.Optional;

public interface MemberRepository {
    Optional<MemberEntity> findById(Long memberId);

}