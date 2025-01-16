package org.signaling.signaling_server.domain.member.mapper;

import org.signaling.signaling_server.domain.member.dto.response.MemberInfoResponse;
import org.signaling.signaling_server.entity.member.MemberEntity;

public class MemberResponseMapper {
    public static MemberInfoResponse toMemberResponseMapper(MemberEntity memberEntity){
        return MemberInfoResponse.builder()
                .username(memberEntity.getUsername())
                .name(memberEntity.getName())
                .nickname(memberEntity.getNickname())
                .email(memberEntity.getEmail())
                .birth(memberEntity.getBirth())
                .createdAt(memberEntity.getCreatedAt().toString())
                .build();
    }
}
