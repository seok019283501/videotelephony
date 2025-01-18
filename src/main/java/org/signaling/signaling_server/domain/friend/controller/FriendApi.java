package org.signaling.signaling_server.domain.friend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.domain.friend.dto.request.FriendIdRequest;
import org.signaling.signaling_server.domain.friend.dto.request.AddFriendRequest;
import org.signaling.signaling_server.domain.friend.dto.response.FriendInfoListResponse;
import org.springframework.security.core.Authentication;

@Tag(name = "Friend Api", description = "친구 관련 API 목록입니다.")
public interface FriendApi {
    @Operation(summary = "친구추가를 합니다.", description = "담당자: 최민석")
    Api<?> addFriend(AddFriendRequest addFriendRequest, Authentication authentication);

    @Operation(summary = "친구추가를 허가합니다.", description = "담당자: 최민석")
    Api<?> acceptFriend(FriendIdRequest friendIdRequest, Authentication authentication);

    @Operation(summary = "친구삭제를 합니다.", description = "담당자: 최민석")
    Api<?> deleteFriend(FriendIdRequest friendIdRequest, Authentication authentication);

    @Operation(summary = "친구를 검색합니다.", description = "담당자: 최민석")
    Api<FriendInfoListResponse> searchToFriend(String nickname, Authentication authentication);

}
