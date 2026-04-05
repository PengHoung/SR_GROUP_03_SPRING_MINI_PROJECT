package org.example.sr_group_03_spring_mini_project.service;

import org.example.sr_group_03_spring_mini_project.model.entity.Achievement;

import java.util.List;
import java.util.UUID;

public interface AchievementService {

    List<Achievement> getAllAchievements(Integer page, Integer size);

    List<Achievement> getCurrentUserAchievements(Integer page, Integer size);

    void evaluateAndGrantAchievements(UUID appUserId);
}
