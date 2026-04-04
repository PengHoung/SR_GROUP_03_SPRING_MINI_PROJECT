package org.example.sr_group_03_spring_mini_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sr_group_03_spring_mini_project.model.entity.AppUser;
import org.example.sr_group_03_spring_mini_project.model.response.AppUserResponse;
import org.example.sr_group_03_spring_mini_project.repository.AuthRepository;
import org.example.sr_group_03_spring_mini_project.repository.ProfileRepository;
import org.example.sr_group_03_spring_mini_project.service.ProfileService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    @Override
    public AppUserResponse getUserProfile() {
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("This is the app user " + appUser);
        return   profileRepository.findById(appUser.getAppUserId());

        //        return AppUserResponse.builder()
//                .appUserId(appUserFromDb.getAppUserId())
//                .userName(appUserFromDb.getUsername())
//                .email(appUserFromDb.getEmail())
//                .level(appUserFromDb.getLevel())
//                .xp(appUserFromDb.getXp())
//                .profileImage(appUser.getProfileImage())
//                .isVerified(appUserFromDb.getIsVerified())
//                .createAt(appUserFromDb.getCreateAt())
//                .build();
    }
}
