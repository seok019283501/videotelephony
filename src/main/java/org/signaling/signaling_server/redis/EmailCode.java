package org.signaling.signaling_server.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@AllArgsConstructor
@RedisHash(value = "emailCode", timeToLive = 60)
public class EmailCode {
    @Id
    private Long id;

    @Indexed
    private String email;

    private String code;
}