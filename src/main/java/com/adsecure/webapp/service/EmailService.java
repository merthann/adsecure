package com.adsecure.webapp.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
