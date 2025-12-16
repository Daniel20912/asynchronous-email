package com.danieloliveira.user_service.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object); // converte o objeto em uma estrutura de json
        } catch (Exception ex) {
            throw new RuntimeException("Error generating Json on producer", ex);
        }
    }
}

