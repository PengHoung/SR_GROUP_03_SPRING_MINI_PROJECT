package org.example.sr_group_03_spring_mini_project.service;

import org.example.sr_group_03_spring_mini_project.model.entity.Profile;
import org.example.sr_group_03_spring_mini_project.model.request.ProfileRequest;
import org.example.sr_group_03_spring_mini_project.model.response.ApiResponse;
import org.example.sr_group_03_spring_mini_project.model.response.AppUserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProfileService {
    AppUserResponse getUserProfile();
    AppUserResponse updateUserProfile(ProfileRequest request);
    void deleteUserProfile();
}
