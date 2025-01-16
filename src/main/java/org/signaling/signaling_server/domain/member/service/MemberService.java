package org.signaling.signaling_server.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.domain.member.dto.CustomUserDetail;
import org.signaling.signaling_server.domain.member.dto.response.MemberInfoResponse;
import org.signaling.signaling_server.domain.member.mapper.MemberResponseMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    //회원정보 조회
    public MemberInfoResponse memberInfo(Authentication authentication) {
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();

        return MemberResponseMapper.toMemberResponseMapper(userDetails.getMemberEntity());
    }
}
