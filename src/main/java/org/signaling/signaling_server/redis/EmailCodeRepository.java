package org.signaling.signaling_server.redis;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmailCodeRepository extends CrudRepository<EmailCode, String> {
    Optional<EmailCode> findByEmail(String email);
}