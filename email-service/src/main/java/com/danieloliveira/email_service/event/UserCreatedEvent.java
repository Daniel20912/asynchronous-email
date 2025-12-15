package com.danieloliveira.email_service.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserCreatedEvent {

    private String name;
    private String email;
}
