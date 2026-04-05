package org.example.sr_group_03_spring_mini_project.exception.auth;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException() {
        super("Email or username already exists.");
    }
    public EntityAlreadyExistsException(String msg){
        super(msg);
    }
}
