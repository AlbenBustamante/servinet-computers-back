package com.servinetcomputers.api.core.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendEmail(String to, String subject, String body) {
        final var message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(from);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);
    }
}
