package org.example.sr_group_03_spring_mini_project.model.response;
import org.springframework.http.HttpStatus;
import java.time.Instant;

public class ApiResponse <T>{
    private Boolean success;
    private String message;
    private HttpStatus status;
    private T payload;
    private Instant instant;
}
