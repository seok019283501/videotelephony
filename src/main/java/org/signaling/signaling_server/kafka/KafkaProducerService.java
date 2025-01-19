package org.signaling.signaling_server.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.signaling.signaling_server.kafka.dto.CallRoomNotification;
import org.signaling.signaling_server.kafka.dto.FriendNotification;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    // 친구 관련 알림 전송 메소드
    public void sendFriendNotification(@Payload FriendNotification notification) {
        try {
            String message = objectMapper.writeValueAsString(notification); // JSON 직렬화
            kafkaTemplate.send("friend", message); // Kafka 토픽에 발행
            log.info("Message published to Kafka: {}", message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    // 방초대 관련 알림 전송 메소드
    public void sendCallRoomNotification(@Payload CallRoomNotification notification) {
        try {
            String message = objectMapper.writeValueAsString(notification); // JSON 직렬화
            kafkaTemplate.send("call-room", message); // Kafka 토픽에 발행
            log.info("Message published to Kafka: {}", message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}