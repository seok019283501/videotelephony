package org.signaling.signaling_server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.common.type.success.MemberSuccessType;
import org.signaling.signaling_server.domain.member.dto.response.MemberInfoResponse;
import org.signaling.signaling_server.domain.member.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApiController implements MemberApi {
    private final MemberService memberService;

    //회원정보 조회
    @GetMapping
    public Api<MemberInfoResponse> memberInfo(Authentication authentication) {
        MemberInfoResponse memberInfoResponse = memberService.memberInfo(authentication);
        return Api.success(MemberSuccessType.MEMBER_INFO,memberInfoResponse);
    }
}
