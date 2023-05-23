package com.calebematos.askfood.core.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("spring.mail")
public class SpringMailProperties {

    private String host;
    private int port;
    private String username;
    private String password;

}
