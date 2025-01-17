package org.signaling.signaling_server.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.kafka.dto.FriendNotification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
    private final SimpMessageSendingOperations messagingTemplate;
    private final ObjectMapper objectMapper;

    // 친구 알림 수신 메소드 추가
    @KafkaListener(topics = "friend", groupId = "friend-group")
    public void consumeFriendNotification(String message) {
        try {
            FriendNotification notification = objectMapper.readValue(
                message, 
                FriendNotification.class
            );
            
            // 친구 요청/수락 알림을 특정 사용자에게 전송
            messagingTemplate.convertAndSend(
                "/sub/friend/" + notification.getToMemberId(),
                notification
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}