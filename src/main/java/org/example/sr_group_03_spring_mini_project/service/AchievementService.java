package org.example.sr_group_03_spring_mini_project.service;

import org.example.sr_group_03_spring_mini_project.model.entity.Achievement;

import java.util.List;

public interface AchievementService {

    List<Achievement> getAllAchievements();

    List<Achievement> getCurrentUserAchievements();
}
