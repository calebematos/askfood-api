package com.calebematos.askfood.core.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("askfood.email")
public class EmailProperties {

    private String sender;
    private Implementation impl;

    public enum Implementation {
        FAKE, SMTP;
    }

}
