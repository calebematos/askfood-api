package com.calebematos.askfood.infrastructure.service.email;

import com.calebematos.askfood.core.email.EmailProperties;
import com.calebematos.askfood.domain.service.SendEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
public class SmtpSendEmailService implements SendEmailService {

    private final JavaMailSender mailSender;
    private final EmailProperties emailProperties;
    private final Configuration freemarkerConfig;

    @Override
    public void sendEmail(Message message) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
            messageHelper.setFrom(emailProperties.getSender());
            messageHelper.setTo(message.getRecipients().toArray(new String[0]));
            messageHelper.setSubject(message.getSubject());
            messageHelper.setText(processTemplate(message), true);

            mailSender.send(mimeMessage);
        }catch (Exception e){
            throw EmailException.of("Could not send email", e);
        }
    }

    private String processTemplate(Message message){
        try {
            Template template = freemarkerConfig.getTemplate(message.getBody());

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, message.getVariables());
        } catch (Exception e) {
            throw EmailException.of("Could not process email template", e);
        }
    }
}
