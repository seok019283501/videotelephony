package org.signaling.signaling_server.domain.friend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.common.type.success.FriendSuccessType;
import org.signaling.signaling_server.domain.friend.dto.request.AddFriendRequest;
import org.signaling.signaling_server.domain.friend.service.FriendService;
import org.signaling.signaling_server.kafka.dto.FriendNotification;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @MessageMapping("/sub/friend/{toMemberId}")
    @SendTo("/pub/friend/{toMemberId}")
    public FriendNotification playVideo(FriendNotification message) {
        return message;
    }
}
