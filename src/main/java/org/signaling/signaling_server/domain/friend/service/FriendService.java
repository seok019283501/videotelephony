package org.signaling.signaling_server.domain.friend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.signaling.signaling_server.common.exception.BadRequestException;
import org.signaling.signaling_server.common.type.error.FriendErrorType;
import org.signaling.signaling_server.domain.friend.dto.request.AddFriendRequest;
import org.signaling.signaling_server.domain.friend.mapper.FriendEntityMapper;
import org.signaling.signaling_server.domain.friend.mapper.FriendResponseMapper;
import org.signaling.signaling_server.domain.friend.repository.FriendRepository;
import org.signaling.signaling_server.domain.member.dto.CustomUserDetail;
import org.signaling.signaling_server.entity.friend.FriendEntity;
import org.signaling.signaling_server.entity.friend.enums.FriendStatus;
import org.signaling.signaling_server.kafka.KafkaProducerService;
import org.signaling.signaling_server.kafka.dto.FriendNotification;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FriendService {
    private final FriendRepository friendRepository;
    private final KafkaProducerService kafkaProducerService; // Kafka Producer 주입
    private final SimpMessageSendingOperations messagingTemplate;

    @Transactional
    public void addFriend(AddFriendRequest addFriendRequest, Authentication authentication) {
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();

        // 친구추가 요청 유무 확인
        if (friendRepository.existsByFromMemberIdAndToMemberIdAndStatus(
                userDetails.getId(), addFriendRequest.toMemberId(), FriendStatus.REQUEST)) {
            throw new BadRequestException(FriendErrorType.FRIEND_REQUEST);
        }

        FriendEntity friendEntity = FriendEntityMapper.toEntity(addFriendRequest, userDetails.getId());
        friendRepository.save(friendEntity);

        // 알림 생성
        FriendNotification notification = FriendResponseMapper.toFriendNotification(
                addFriendRequest, userDetails.getMemberEntity(), FriendStatus.REQUEST,
                userDetails.getMemberEntity().getNickname() + "님이 친구요청을 하였습니다."
        );

        // Kafka에 발행
        kafkaProducerService.sendFriendNotification(notification);
    }
}