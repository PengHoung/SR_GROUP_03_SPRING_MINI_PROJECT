package org.example.sr_group_03_spring_mini_project.exception;


import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException extends RuntimeException {
    private final String title;
    private final String type;
    private final HttpStatus status;

    public ApiException(String detail, String title, String type, HttpStatus status) {
        super(detail);
        this.title = title;
        this.type = type;
        this.status = status;
    }

    public static ApiException notFound(String title) {
        return new ApiException(title,
                "Not Found",
                "http://localhost:8080/errors/not-found",
                HttpStatus.NOT_FOUND);
    }

    public static ApiException conflict(String title) {
        return new ApiException(title,
                "Conflict",
                "http://localhost:8080/errors/operation-not-allowed",
                HttpStatus.CONFLICT);
    }


}


