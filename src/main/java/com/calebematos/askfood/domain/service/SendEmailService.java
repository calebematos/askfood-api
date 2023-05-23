package com.calebematos.askfood.domain.service;

import lombok.*;

import java.util.Map;
import java.util.Set;

public interface SendEmailService {

    void sendEmail(Message message);

    @Getter
    @Setter
    @Builder
    class Message {

        @Singular
        private Set<String> recipients;

        @NonNull
        private String subject;

        @NonNull
        private String body;

        @Singular
        private Map<String, Object> variables;
    }
}
