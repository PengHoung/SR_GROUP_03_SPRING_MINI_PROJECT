package org.example.sr_group_03_spring_mini_project.controller;

import jakarta.validation.Valid;
import org.example.sr_group_03_spring_mini_project.model.request.AppUserRequest;
import org.example.sr_group_03_spring_mini_project.model.request.AuthRequest;
import org.example.sr_group_03_spring_mini_project.model.response.ApiResponse;
import org.example.sr_group_03_spring_mini_project.model.response.AppUserResponse;
import org.example.sr_group_03_spring_mini_project.model.response.TokenResponse;
import org.example.sr_group_03_spring_mini_project.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auths")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@RequestBody @Valid AuthRequest request) {
        ApiResponse<TokenResponse> res = ApiResponse.success("Login successful! Authentication token generated.", HttpStatus.OK, authService.login(request));
        return ResponseEntity.ok(res);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AppUserResponse>> register(@RequestBody @Valid AppUserRequest request) {
        ApiResponse<AppUserResponse> res = ApiResponse.success("User registered successfully! Please verify your email to complete the registration.", HttpStatus.OK, authService.register(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<Void>> verify(@RequestParam String email, @RequestParam String otp) {
        authService.verifyOtp(email, otp);
        return ResponseEntity.ok(ApiResponse.success("Email successfully verified! You can now log in.", HttpStatus.OK));
    }

    @PostMapping("/resend")
    public ResponseEntity<ApiResponse<Void>> resendEmail(@RequestParam String email) {
        authService.resendOtp(email);
        return ResponseEntity.ok(ApiResponse.success("Verification OTP successfully resent to your email.", HttpStatus.OK));
    }
}
