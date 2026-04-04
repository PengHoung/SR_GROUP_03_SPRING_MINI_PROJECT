package org.example.sr_group_03_spring_mini_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sr_group_03_spring_mini_project.model.entity.Achievement;
import org.example.sr_group_03_spring_mini_project.repository.AchievementRepository;
import org.example.sr_group_03_spring_mini_project.service.AchievementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;

    @Override
    public List<Achievement> getAllAchievements() {
        return achievementRepository.getAllAchievements();
    }

    @Override
    public List<Achievement> getCurrentUserAchievements() {
        return achievementRepository.getCurrentUserAchievements();
    }
}
