package org.example.sr_group_03_spring_mini_project.exception.auth;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("Email or username already exists.");
    }
}
