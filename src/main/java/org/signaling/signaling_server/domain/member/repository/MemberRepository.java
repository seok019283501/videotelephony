package org.signaling.signaling_server.domain.member.repository;

import org.signaling.signaling_server.entity.member.MemberEntity;

import java.util.Optional;

public interface MemberRepository {
    Optional<MemberEntity> findById(Long memberId);

    boolean existsByEmail(String Email);

    boolean existsByUsername(String username);

    void save(MemberEntity memberEntity);

    Optional<MemberEntity> findByUsername(String username);

    void updatePasswordByEmail(String password, String email);

    void updatePasswordById(String password, Long id);

    Optional<MemberEntity> findByEmail(String email);

    void updateNicknameByUId(Long id, String nickname);
}