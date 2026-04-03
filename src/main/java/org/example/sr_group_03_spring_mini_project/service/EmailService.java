package org.example.sr_group_03_spring_mini_project.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendOtp(String toEmail, String otp) throws MessagingException;
}
