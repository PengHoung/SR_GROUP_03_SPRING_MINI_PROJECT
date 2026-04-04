package org.example.sr_group_03_spring_mini_project.exception.auth;

public class InvalidOtpException extends RuntimeException {
    public InvalidOtpException(String message) {
        super(message);
    }
}
