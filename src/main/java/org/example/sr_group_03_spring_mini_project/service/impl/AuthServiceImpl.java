package org.example.sr_group_03_spring_mini_project.service.impl;

import lombok.SneakyThrows;
import org.example.sr_group_03_spring_mini_project.exception.auth.*;
import org.example.sr_group_03_spring_mini_project.model.entity.AppUser;
import org.example.sr_group_03_spring_mini_project.model.request.AppUserRequest;
import org.example.sr_group_03_spring_mini_project.model.request.AuthRequest;
import org.example.sr_group_03_spring_mini_project.model.response.AppUserResponse;
import org.example.sr_group_03_spring_mini_project.model.response.TokenResponse;
import org.example.sr_group_03_spring_mini_project.repository.AuthRepository;
import org.example.sr_group_03_spring_mini_project.service.AuthService;
import org.example.sr_group_03_spring_mini_project.service.EmailService;
import org.example.sr_group_03_spring_mini_project.utils.CacheUtils;
import org.example.sr_group_03_spring_mini_project.utils.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

import static org.example.sr_group_03_spring_mini_project.utils.CacheUtils.OTP_CACHE_NAME.EMAIL_CACHE;
import static org.example.sr_group_03_spring_mini_project.utils.CacheUtils.OTP_CACHE_NAME.USER_CACHE;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final EmailService emailService;
    private final CacheUtils cacheUtils;

    public AuthServiceImpl(AuthRepository authRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, EmailService emailService, CacheUtils cacheUtils) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.emailService = emailService;
        this.cacheUtils = cacheUtils;
    }


    @Override
    public TokenResponse login(AuthRequest request) {
        AppUser appUser = authRepository.getAppUserByIdentifier(request.getIdentifier());

        if (appUser == null) throw new UserNotFoundException();
        if (!appUser.getIsVerified()) throw new EmailNotVerifiedException();

        if (!passwordEncoder.matches(request.getPassword(), appUser.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtUtils.generateToken(appUser);
        return new TokenResponse(token);
    }


    @Override
    @SneakyThrows
    public AppUserResponse register(AppUserRequest request) {
        if (authRepository.existsByEmailOrUsername(request.getEmail(), request.getUsername())) {
            throw new UserAlreadyExistsException();
        }


        AppUser newUser = AppUser.builder()
                .appUserId(UUID.randomUUID())
                .appUsername(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .profileImage(request.getProfileImageUrl())
                .xp(0L)
                .level(1L)
                .isVerified(false)
                .createAt(LocalDate.now())
                .build();

        cacheUtils.put(USER_CACHE, request.getEmail(), newUser);

        String otp = generateOtp();
        cacheUtils.put(EMAIL_CACHE, newUser.getEmail(), otp);
        emailService.sendOtp(newUser.getEmail(), otp);

        return AppUserResponse.builder()
                .xp(newUser.getXp())
                .level(newUser.getLevel())
                .appUserId(newUser.getAppUserId())
                .userName(newUser.getAppUsername())
                .email(newUser.getEmail())
                .isVerified(newUser.getIsVerified())
                .profileImage(newUser.getProfileImage())
                .createAt(newUser.getCreateAt())
                .build();
    }

    @SneakyThrows
    @Override
    public void resendOtp(String email) {
        if (authRepository.existsByEmail(email)) {
            throw new UserAlreadyVerifiedException();

        }
        String otp = generateOtp();
        cacheUtils.put(EMAIL_CACHE, email, otp);
        emailService.sendOtp(email, otp);
    }

    @Transactional
    @Override
    public void verifyOtp(String email, String otp) {

        String storedOtp = cacheUtils.get(EMAIL_CACHE, email, String.class);

        if (storedOtp == null) {
            throw new InvalidOtpException("OTP has expired. Please request a new one.");
        }

        if (!storedOtp.equals(otp)) {
            throw new InvalidOtpException("Invalid OTP.");
        }
        AppUser pendingUser = cacheUtils.get(USER_CACHE, email, AppUser.class);
        if (pendingUser == null) {
            throw new UserNotFoundException();
        }
        pendingUser.setIsVerified(true);

        authRepository.saveUser(pendingUser);

        cacheUtils.evict(EMAIL_CACHE, email);
        cacheUtils.evict(USER_CACHE, email);

    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

}
