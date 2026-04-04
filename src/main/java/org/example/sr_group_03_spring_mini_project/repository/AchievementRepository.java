package org.example.sr_group_03_spring_mini_project.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.sr_group_03_spring_mini_project.model.entity.Achievement;

import java.util.List;

@Mapper
public interface AchievementRepository {

    @Select("""
        SELECT
            achievement_id AS achievementId,
            title,
            description,
            badge,
            xp_required
        FROM achievements
        ORDER BY achievement_id
    """)
    List<Achievement> getAllAchievements();

    @Select("""
        SELECT
            a.achievement_id AS achievementId,
            a.title,
            a.description,
            a.badge,
            a.xp_required
        FROM achievements a
        JOIN app_user_achievements aua
            ON a.achievement_id = aua.achievement_id
        WHERE aua.app_user_id = 1
        ORDER BY a.achievement_id
    """)
    List<Achievement> getCurrentUserAchievements();
}
