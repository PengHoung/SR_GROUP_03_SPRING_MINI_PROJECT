package org.example.sr_group_03_spring_mini_project.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserRequest {

    @NotBlank
    @NotNull
    private String username;
    @NotBlank
    @NotNull
    @Email
    private String email;
    @NotBlank
    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$#!%*?&]{6,}$",message = "Password must contain uppercase, lowercase, number, and special character with minimum 6 characters.")
    private String password;
    private String profileImageUrl;
}
