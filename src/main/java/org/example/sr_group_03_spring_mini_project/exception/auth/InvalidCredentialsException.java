package org.example.sr_group_03_spring_mini_project.exception.auth;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Invalid username or password.");
    }
}
