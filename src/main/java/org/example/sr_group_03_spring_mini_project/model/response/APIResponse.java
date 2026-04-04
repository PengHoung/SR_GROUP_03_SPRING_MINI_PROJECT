package org.example.sr_group_03_spring_mini_project.model.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse<T> {
    private boolean success;
    private HttpStatus status;
    private String message;
    private T payload;
    private Instant timestamp;
}

