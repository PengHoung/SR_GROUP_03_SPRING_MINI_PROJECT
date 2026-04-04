package org.example.sr_group_03_spring_mini_project.controller;

import lombok.RequiredArgsConstructor;
import org.example.sr_group_03_spring_mini_project.model.entity.Profile;
import org.example.sr_group_03_spring_mini_project.model.response.ApiResponse;
import org.example.sr_group_03_spring_mini_project.model.response.AppUserResponse;
import org.example.sr_group_03_spring_mini_project.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;



    @GetMapping
    public ResponseEntity<ApiResponse<AppUserResponse>> getUserProfile(){
        AppUserResponse appUserResponse = profileService.getUserProfile();
        ApiResponse<AppUserResponse> response = ApiResponse.<AppUserResponse>builder()
                .success(true)
                .message("Courses retrieved successfully")
                .status(String.valueOf(HttpStatus.OK))
                .payload(appUserResponse)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.ok(response);
    }
}
