package org.example.sr_group_03_spring_mini_project.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ApiResponse<T> {
    private Instant timestamp;
    private String message;
    private String status;
    private Boolean success;
    private T payload;

    public static <T> ApiResponse<T> success(String message, HttpStatus status, T payload) {
        return ApiResponse.<T>builder()
                .success(true)
                .timestamp(Instant.now())
                .message(message)
                .status(status.getReasonPhrase())
                .payload(payload)
                .build();
    }

    public static <T> ApiResponse<T> success(String message, HttpStatus status) {
        return ApiResponse.<T>builder()
                .success(true)
                .timestamp(Instant.now())
                .message(message)
                .status(status.getReasonPhrase())
                .payload(null)
                .build();
    }


}