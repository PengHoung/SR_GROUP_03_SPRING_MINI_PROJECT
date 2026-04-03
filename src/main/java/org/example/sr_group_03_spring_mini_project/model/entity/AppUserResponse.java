package org.example.sr_group_03_spring_mini_project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserResponse {
    private UUID appUserId;
    private String userName;
    private String email;
    private String password;
    private Long level;
    private Long xp;
    private String profileImage;
    private Boolean isVerified;
    private LocalDate createAt;


}
