package org.signaling.signaling_server.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.signaling.signaling_server.kafka.dto.FriendNotification;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    // 친구 관련 알림 전송 메소드
    public void sendFriendNotification(FriendNotification notification) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(notification);
            kafkaTemplate.send("friend", jsonMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}