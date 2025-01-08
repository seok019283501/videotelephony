package org.signaling.signaling_server.redis;

import org.springframework.data.repository.CrudRepository;

public interface AccessTokenRepository extends CrudRepository<AccessToken, String> {
    boolean existsByAccessToken(String accessToken);
}