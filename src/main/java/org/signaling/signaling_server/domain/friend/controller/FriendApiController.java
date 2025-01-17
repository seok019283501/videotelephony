package org.signaling.signaling_server.domain.friend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.common.type.success.FriendSuccessType;
import org.signaling.signaling_server.domain.friend.dto.request.FriendIdRequest;
import org.signaling.signaling_server.domain.friend.dto.request.AddFriendRequest;
import org.signaling.signaling_server.domain.friend.service.FriendService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friend")
public class FriendApiController implements FriendApi {
    private final FriendService friendService;



    @PostMapping
    public Api<?> addFriend(
            @Valid
            @RequestBody AddFriendRequest addFriendRequest,
            Authentication authentication
    ) {
        friendService.addFriend(addFriendRequest, authentication);
        return Api.success(FriendSuccessType.ADD_FRIEND);
    }

    @PatchMapping("/accept")
    public Api<?> acceptFriend(
            @Valid
            @RequestBody FriendIdRequest friendIdRequest,
            Authentication authentication
    ) {
        friendService.acceptFriend(friendIdRequest, authentication);
        return Api.success(FriendSuccessType.ACCEPT_ADD_FRIEND);
    }

    @DeleteMapping
    public Api<?> deleteFriend(
            @Valid
            @RequestBody FriendIdRequest friendIdRequest,
            Authentication authentication
    ) {
        friendService.deleteFriend(friendIdRequest, authentication);
        return Api.success(FriendSuccessType.DELETE_FRIEND);
    }
}
