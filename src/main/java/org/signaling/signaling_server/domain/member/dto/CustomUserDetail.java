package org.signaling.signaling_server.domain.member.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.entity.member.MemberEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@RequiredArgsConstructor
public class CustomUserDetail implements UserDetails {
    private MemberEntity memberEntity;

    public CustomUserDetail(MemberEntity memberEntity) {
        this.memberEntity = memberEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return memberEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return memberEntity.getUsername();
    }

    public Long getId() {
        return memberEntity.getId();
    }
}