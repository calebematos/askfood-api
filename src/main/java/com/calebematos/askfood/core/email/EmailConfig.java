package com.calebematos.askfood.core.email;

import com.calebematos.askfood.domain.service.SendEmailService;
import com.calebematos.askfood.infrastructure.service.email.FakeSendEmailService;
import com.calebematos.askfood.infrastructure.service.email.SmtpSendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class EmailConfig {

    private final EmailProperties emailProperties;
    private final SpringMailProperties springMailProperties;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(springMailProperties.getHost());
        mailSender.setPort(springMailProperties.getPort());

        mailSender.setUsername(springMailProperties.getUsername());
        mailSender.setPassword(springMailProperties.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");

        return mailSender;
    }

      @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
          freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates");
        return freeMarkerConfigurer;
    }

    @Bean
    public SendEmailService emailService() {
        return switch (emailProperties.getImpl()) {
            case FAKE -> new FakeSendEmailService();
            case SMTP -> new SmtpSendEmailService(
                    javaMailSender(), emailProperties,
                    freemarkerConfig().getConfiguration());
            default -> null;
        };
    }
}
