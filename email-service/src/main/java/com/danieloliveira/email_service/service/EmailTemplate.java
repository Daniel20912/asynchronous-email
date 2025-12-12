package com.danieloliveira.email_service.service;

import org.springframework.stereotype.Service;

@Service
public class EmailTemplate {

    private static final String SUBJECT = "Hello %s";

    private static final String TEXT = """
            Welcome to our system!
            
            It's a pleasure to have you with us, %s!
            
            Your registration was successful and you can now enjoy all the available features.
            
            If you have any questions, please feel free to contact us.
            
            Sincerely,
            Support Team""";

    public String buildSubject(String name) {
        return String.format(SUBJECT, name);
    }

    public String buildText(String name) {
        return String.format(TEXT, name);
    }
}
