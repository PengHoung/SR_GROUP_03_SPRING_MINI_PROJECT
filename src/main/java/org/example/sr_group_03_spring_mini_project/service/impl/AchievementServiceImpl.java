package org.example.sr_group_03_spring_mini_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sr_group_03_spring_mini_project.exception.auth.InvalidCredentialsException;
import org.example.sr_group_03_spring_mini_project.exception.auth.UserNotFoundException;
import org.example.sr_group_03_spring_mini_project.model.entity.Achievement;
import org.example.sr_group_03_spring_mini_project.model.entity.AppUser;
import org.example.sr_group_03_spring_mini_project.repository.AchievementRepository;
import org.example.sr_group_03_spring_mini_project.service.AchievementService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;

    @Override
    public List<Achievement> getAllAchievements(Integer page, Integer size) {
        Integer offset = (page - 1) * size;
        return achievementRepository.getAllAchievements(size, offset);
    }

    @Override
    public List<Achievement> getCurrentUserAchievements(Integer page, Integer size) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser appUser = CheckIsAuthenticated();

        Integer offset = (page - 1) * size;
        return achievementRepository.getCurrentUserAchievements(appUser.getAppUserId(), size, offset);
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
