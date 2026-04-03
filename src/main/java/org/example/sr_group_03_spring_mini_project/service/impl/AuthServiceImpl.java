package org.example.sr_group_03_spring_mini_project.service.impl;

import org.example.sr_group_03_spring_mini_project.model.entity.AppUser;
import org.example.sr_group_03_spring_mini_project.model.request.AppUserRequest;
import org.example.sr_group_03_spring_mini_project.model.request.AuthRequest;
import org.example.sr_group_03_spring_mini_project.model.response.TokenResponse;
import org.example.sr_group_03_spring_mini_project.repository.AuthRepository;
import org.example.sr_group_03_spring_mini_project.service.AuthService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;

    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {

        return authRepository.getAppUserByIdentifier(identifier);
    }

    @Override
    public TokenResponse login(AuthRequest request) {
        return null;
    }

    @Override
    public AppUser register(AppUserRequest request) {
        return null;
    }

    @Override
    public void resendOtp(String email) {

    }

    @Override
    public void verifyOtp(String email, String otp) {

    }
}
