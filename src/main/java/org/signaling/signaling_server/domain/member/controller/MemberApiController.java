package org.signaling.signaling_server.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.common.type.success.MemberSuccessType;
import org.signaling.signaling_server.domain.member.dto.request.ChangeNicknameRequest;
import org.signaling.signaling_server.domain.member.dto.response.MemberInfoResponse;
import org.signaling.signaling_server.domain.member.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    //닉네임 변경
    @PatchMapping("/change-nickname")
    public Api<?> changeNickname(
            Authentication authentication,
            @Valid
            @RequestBody ChangeNicknameRequest changeNicknameRequest
    ) {
        memberService.changeNickname(authentication, changeNicknameRequest);
        return Api.success(MemberSuccessType.CHANGE_NICKNAME);
    }

}
