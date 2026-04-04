package org.example.sr_group_03_spring_mini_project.service;

import jakarta.mail.MessagingException;
import org.example.sr_group_03_spring_mini_project.model.entity.AppUser;
import org.example.sr_group_03_spring_mini_project.model.request.AppUserRequest;
import org.example.sr_group_03_spring_mini_project.model.request.AuthRequest;
import org.example.sr_group_03_spring_mini_project.model.response.AppUserResponse;
import org.example.sr_group_03_spring_mini_project.model.response.TokenResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService {

    TokenResponse login(AuthRequest request);

    AppUserResponse register(AppUserRequest request) ;

    void resendOtp(String email);

    void verifyOtp(String email, String otp);
}
