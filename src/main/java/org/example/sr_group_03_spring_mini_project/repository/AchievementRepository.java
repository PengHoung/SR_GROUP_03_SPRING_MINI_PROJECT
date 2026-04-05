package org.example.sr_group_03_spring_mini_project.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.sr_group_03_spring_mini_project.model.entity.Achievement;

import java.util.List;
import java.util.UUID;

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
                LIMIT #{size} OFFSET #{offset}
            """)
    List<Achievement> getAllAchievements(
            @Param("size") Integer size,
            @Param("offset") Integer offset
    );

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
                WHERE aua.app_user_id = #{userId}
                ORDER BY a.achievement_id
                LIMIT #{size} OFFSET #{offset}
            """)
    List<Achievement> getCurrentUserAchievements(
            @Param("userId") UUID userId,
            @Param("size") Integer size,
            @Param("offset") Integer offset
    );

    @Insert("""
                INSERT INTO app_user_achievements (app_user_id, achievement_id)
                VALUES (
                    #{appUserId}::uuid,
                    #{achievementId}::uuid
                )
                ON CONFLICT (app_user_id, achievement_id) DO NOTHING
            """)
    void awardAchievementToUser(
            @Param("appUserId") UUID appUserId,
            @Param("achievementId") UUID achievementId
    );

    @Select("""
                SELECT
                    a.achievement_id AS achievementId,
                    a.title,
                    a.description,
                    a.badge,
                    a.xp_required AS xpRequired
                FROM achievements a
                WHERE a.xp_required <= #{currentXp}
                  AND NOT EXISTS (
                      SELECT 1 FROM app_user_achievements aua
                      WHERE aua.achievement_id = a.achievement_id
                        AND aua.app_user_id = #{appUserId}::uuid
                  )
            """)
    List<Achievement> findUnlockedButNotYetAwardedAchievements(
            @Param("appUserId") UUID appUserId,
            @Param("currentXp") long currentXp
    );

}
