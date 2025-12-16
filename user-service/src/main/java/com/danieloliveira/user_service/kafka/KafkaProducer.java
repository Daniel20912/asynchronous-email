package com.danieloliveira.user_service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.user-created-v1}")
    private String userCreatedTopic;

    public void sendMessage(String payload) {
        try {
            log.info("Sending message to topic: {}", userCreatedTopic);
            kafkaTemplate.send(userCreatedTopic, payload);
            log.info("Message sent to topic: {}", userCreatedTopic);
        } catch (Exception e) {
            log.error("Error sending message to topic: {}", userCreatedTopic, e);
        }
    }
}
