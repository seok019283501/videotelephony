package org.signaling.signaling_server.domain.friend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.domain.auth.dto.response.SignInResponse;
import org.signaling.signaling_server.domain.friend.dto.request.AcceptFriendRequest;
import org.signaling.signaling_server.domain.friend.dto.request.AddFriendRequest;
import org.springframework.security.core.Authentication;

@Tag(name = "Friend Api", description = "친구 관련 API 목록입니다.")
public interface FriendApi {
    @Operation(summary = "친구추가를 합니다.", description = "담당자: 최민석")
    Api<?> addFriend(AddFriendRequest addFriendRequest, Authentication authentication);

    @Operation(summary = "친구추가를 허가합니다.", description = "담당자: 최민석")
    Api<?> acceptFriend(AcceptFriendRequest acceptFriendRequest, Authentication authentication);
}
