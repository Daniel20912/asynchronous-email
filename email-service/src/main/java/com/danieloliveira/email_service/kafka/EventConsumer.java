package com.danieloliveira.email_service.kafka;

import com.danieloliveira.email_service.service.EmailSender;
import com.danieloliveira.email_service.service.EmailTemplate;
import com.danieloliveira.email_service.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class EventConsumer {

    private final EmailTemplate emailTemplate;
    private final EmailSender emailSender;
    private final JsonUtil jsonUtil;


    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.user-created-v1}"
    )
    public void consumeUserCreatedV1(String payload) {
        log.info("Received UserCreatedEvent from user-created-v1. Trying to send email...");

        try {

            var event = jsonUtil.toUserCreatedEvent(payload);

            String name = event.getName();
            String to = event.getEmail();

            String subject = emailTemplate.buildSubject(name);
            String text = emailTemplate.buildText(name);

            emailSender.sendEmail(to, subject, text);

            log.info("Email sent!");
        } catch (Exception e) {
            log.error("Error while sending email", e);
        }

    }
}
