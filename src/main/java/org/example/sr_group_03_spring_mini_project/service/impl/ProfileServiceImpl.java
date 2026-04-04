package org.example.sr_group_03_spring_mini_project.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.sr_group_03_spring_mini_project.exception.auth.InvalidCredentialsException;
import org.example.sr_group_03_spring_mini_project.exception.auth.UserNotFoundException;
import org.example.sr_group_03_spring_mini_project.model.entity.AppUser;
import org.example.sr_group_03_spring_mini_project.model.request.ProfileRequest;
import org.example.sr_group_03_spring_mini_project.model.response.AppUserResponse;
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
        AppUser appUser = CheckIsAuthenticated();
        System.out.println("This is the app user " + appUser);
        return profileRepository.findById(appUser.getAppUserId());

    }

    @Override
    public AppUserResponse updateUserProfile(ProfileRequest request) {
        AppUser appUser = CheckIsAuthenticated();
        return profileRepository.updateUserProfile(appUser.getAppUserId(), request);
    }

    @Override
    @SneakyThrows
    public void deleteUserProfile() {
        AppUser appUser = CheckIsAuthenticated();
        profileRepository.deleteUserProfile(appUser.getAppUserId());
        SecurityContextHolder.clearContext();
    }

    private AppUser CheckIsAuthenticated() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new InvalidCredentialsException();
        }
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (appUser == null) {
            throw new UserNotFoundException();
        }
        return appUser;
    }
}
