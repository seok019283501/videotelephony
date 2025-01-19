package org.signaling.signaling_server.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Call;
import org.signaling.signaling_server.kafka.dto.CallRoomNotification;
import org.signaling.signaling_server.kafka.dto.FriendNotification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {
    private final SimpMessageSendingOperations messagingTemplate;

    // 친구 알림 수신 메소드 추가
    @KafkaListener(topics = "friend", groupId = "friend-group")
    public void consumeFriendNotification(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            FriendNotification notification = objectMapper.readValue(message, FriendNotification.class);
            log.info("Kafka message consumed: {}", notification);
            messagingTemplate.convertAndSend(
                    "/sub/friend/" + notification.toMemberId(),
                    notification
            );
        } catch (Exception e) {
            log.error("Error sending WebSocket message: ", e);
        }
    }

    // 친구 알림 수신 메소드 추가
    @KafkaListener(topics = "call-room", groupId = "call-room-group")
    public void consumeCallRoomNotification(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CallRoomNotification notification = objectMapper.readValue(message, CallRoomNotification.class);
            log.info("Kafka message consumed: {}", notification);
            messagingTemplate.convertAndSend(
                    "/sub/call-room/" + notification.memberId() + "/invite",
                    notification
            );
        } catch (Exception e) {
            log.error("Error sending WebSocket message: ", e);
        }
    }
}