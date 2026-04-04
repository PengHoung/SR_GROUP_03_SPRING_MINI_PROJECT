package org.example.sr_group_03_spring_mini_project.controller;

import lombok.RequiredArgsConstructor;
import org.example.sr_group_03_spring_mini_project.model.entity.AppUser;
import org.example.sr_group_03_spring_mini_project.model.request.ProfileRequest;
import org.example.sr_group_03_spring_mini_project.model.response.ApiResponse;
import org.example.sr_group_03_spring_mini_project.model.response.AppUserResponse;
import org.example.sr_group_03_spring_mini_project.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;



    @GetMapping
    public ResponseEntity<ApiResponse<AppUserResponse>> getUserProfile(){
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("This is the app user " + appUser);
        AppUserResponse appUserResponse = profileService.getUserProfile();
        ApiResponse<AppUserResponse> response = ApiResponse.<AppUserResponse>builder()
                .success(true)
                .message("Get User-Profile successfully")
                .status(String.valueOf(HttpStatus.OK))
                .payload(appUserResponse)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public  ResponseEntity<ApiResponse<AppUserResponse>> UpdateUserProfile(@RequestBody ProfileRequest request){
        AppUserResponse appUserResponse = profileService.updateUserProfile(request);
        ApiResponse<AppUserResponse> response = ApiResponse.<AppUserResponse>builder()
                .success(true)
                .message("Update User-Profile successfully")
                .status(String.valueOf(HttpStatus.OK))
                .payload(appUserResponse)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteUserProfile(){
        profileService.deleteUserProfile();
        ApiResponse<Void> response = ApiResponse
                .success("Delete User-Profile successfully",HttpStatus.OK);
        return ResponseEntity.ok(response);
    }


}
