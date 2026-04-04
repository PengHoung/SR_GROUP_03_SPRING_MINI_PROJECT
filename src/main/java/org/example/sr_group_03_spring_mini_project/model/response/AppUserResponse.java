package org.example.sr_group_03_spring_mini_project.model.response;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserResponse {
    private UUID appUserId;
    private String userName;
    private String email;
    private Long level;
    private Long xp;
    private String profileImage;
    private Boolean isVerified;
    private LocalDate createAt;
}
