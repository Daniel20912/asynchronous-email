package com.danieloliveira.email_service.utils;

import com.danieloliveira.email_service.event.UserCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public UserCreatedEvent toUserCreatedEvent(String json) {
        try {
            return objectMapper.readValue(json, UserCreatedEvent.class); // converte o json para o objeto
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
