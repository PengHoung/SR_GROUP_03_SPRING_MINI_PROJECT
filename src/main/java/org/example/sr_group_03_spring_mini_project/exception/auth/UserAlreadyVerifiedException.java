package org.example.sr_group_03_spring_mini_project.exception.auth;


public class UserAlreadyVerifiedException extends RuntimeException {
    public UserAlreadyVerifiedException() {
        super("User is already verified.");
    }
}
