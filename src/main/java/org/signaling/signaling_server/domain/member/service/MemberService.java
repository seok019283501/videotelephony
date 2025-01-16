package org.signaling.signaling_server.domain.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.domain.member.dto.CustomUserDetail;
import org.signaling.signaling_server.domain.member.dto.request.ChangeNicknameRequest;
import org.signaling.signaling_server.domain.member.dto.response.MemberInfoResponse;
import org.signaling.signaling_server.domain.member.mapper.MemberResponseMapper;
import org.signaling.signaling_server.domain.member.repository.MemberRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    //회원정보 조회
    public MemberInfoResponse memberInfo(Authentication authentication) {
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();

        return MemberResponseMapper.toMemberResponseMapper(userDetails.getMemberEntity());
    }

    //닉네임 변경
    @Transactional
    public void changeNickname(Authentication authentication, ChangeNicknameRequest changeNicknameRequest) {
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        memberRepository.updateNicknameByUId(userDetails.getId(),changeNicknameRequest.nickname());
    }
}
