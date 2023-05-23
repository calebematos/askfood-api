package com.calebematos.askfood.infrastructure.service.email;

import com.calebematos.askfood.domain.service.SendEmailService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeSendEmailService implements SendEmailService {

    @Override
    public void sendEmail(Message message) {
        log.info("Sending email...");
    }

}
