package org.signaling.signaling_server.entity.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "member")
@EntityListeners(AuditingEntityListener.class)
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String email;
    private String birth;
}
