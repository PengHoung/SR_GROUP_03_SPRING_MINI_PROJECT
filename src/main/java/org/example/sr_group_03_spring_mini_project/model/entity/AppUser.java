package org.example.sr_group_03_spring_mini_project.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppUser implements UserDetails {
    private UUID appUserId;
    private String appUsername;
    private String email;
    private String password;
    private Long level;
    private Long xp;
    private String profileImage;
    private Boolean isVerified;
    private LocalDate createAt;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getAppUsername() {
        return appUsername;
    }
}
