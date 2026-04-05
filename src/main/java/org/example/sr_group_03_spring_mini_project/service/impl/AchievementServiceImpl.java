package org.example.sr_group_03_spring_mini_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sr_group_03_spring_mini_project.exception.auth.InvalidCredentialsException;
import org.example.sr_group_03_spring_mini_project.exception.auth.UserNotFoundException;
import org.example.sr_group_03_spring_mini_project.model.entity.Achievement;
import org.example.sr_group_03_spring_mini_project.model.entity.AppUser;
import org.example.sr_group_03_spring_mini_project.model.response.AppUserResponse;
import org.example.sr_group_03_spring_mini_project.repository.AchievementRepository;
import org.example.sr_group_03_spring_mini_project.repository.ProfileRepository;
import org.example.sr_group_03_spring_mini_project.service.AchievementService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final ProfileRepository profileRepository;

    @Override
    public List<Achievement> getAllAchievements(Integer page, Integer size) {
        Integer offset = (page - 1) * size;
        return achievementRepository.getAllAchievements(size, offset);
    }


    @Override
    public List<Achievement> getCurrentUserAchievements(Integer page, Integer size) {
        AppUser appUser = CheckIsAuthenticated();

        Integer offset = (page - 1) * size;
        return achievementRepository.getCurrentUserAchievements(appUser.getAppUserId(), size, offset);
    }

    @Override
    public void evaluateAndGrantAchievements(UUID appUserId) {
        AppUserResponse user = profileRepository.findById(appUserId);

        if (user == null) {
            throw new RuntimeException("User not found: " + appUserId);
        }
        int totalXp = Math.toIntExact(((user.getLevel() - 1) * 100) + user.getXp());

        List<Achievement> toGrant = achievementRepository
                .findUnlockedButNotYetAwardedAchievements(appUserId, totalXp);

        for (Achievement achievement : toGrant) {
            achievementRepository.awardAchievementToUser(appUserId, achievement.getAchievementId());
        }
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
