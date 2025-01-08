package org.signaling.signaling_server.domain.member.repository;

import org.signaling.signaling_server.entity.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<MemberEntity, Long> {
}