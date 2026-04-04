package org.example.sr_group_03_spring_mini_project.exception.auth;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User does not exists.");
    }
}
