package org.example.sr_group_03_spring_mini_project.exception.auth;

public class EmailNotVerifiedException extends RuntimeException {
    public EmailNotVerifiedException() {
        super("Email is not verified. Please check your inbox for the OTP.");
    }
}