package org.example.sr_group_03_spring_mini_project.model.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserRequest {
    private String username;
    private String email;
    private String password;
    private String profileImageUrl;
}
