package org.signaling.signaling_server.domain.member.repository;

import org.signaling.signaling_server.entity.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMemberRepository extends JpaRepository<MemberEntity, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<MemberEntity> findByUsername(String username);
    Optional<MemberEntity> findByEmail(String email);
}