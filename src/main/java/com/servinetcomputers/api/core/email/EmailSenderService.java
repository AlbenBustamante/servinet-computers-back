package com.servinetcomputers.api.core.email;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String body);
}
