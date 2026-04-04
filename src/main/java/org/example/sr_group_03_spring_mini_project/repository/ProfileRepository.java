package org.example.sr_group_03_spring_mini_project.repository;

import org.apache.ibatis.annotations.*;
import org.example.sr_group_03_spring_mini_project.model.request.ProfileRequest;
import org.example.sr_group_03_spring_mini_project.model.response.AppUserResponse;

import java.util.UUID;

@Mapper
public interface ProfileRepository {
    String responseMapper = "responseMapper";

    @Results(id = responseMapper, value = {
            @Result(property = "appUserId", column = "app_user_id"),
            @Result(property = "userName", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "level", column = "level"),
            @Result(property = "xp", column = "xp"),
            @Result(property = "profileImage", column = "profile_image"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createAt", column = "created_at")
    })
    @Select("SELECT * FROM app_users WHERE app_user_id = #{id}")
    AppUserResponse findById(UUID id);

    @ResultMap(responseMapper)
    @Select("""
             UPDATE app_users SET username = #{req.userName}, profile_image = #{req.profileImageUrl} WHERE app_user_id = #{id};
            """)
    AppUserResponse updateUserProfile(UUID id, @Param("req") ProfileRequest request);

    @Update("UPDATE app_users SET xp = xp + #{xp} WHERE app_user_id = #{id}")
    void addXpToUser(@Param("id") UUID id, @Param("xp") int xp);

    @Update("""
                UPDATE app_users
                SET level = level + 1, xp = xp - (level * 100)
                WHERE app_user_id = #{id}
                AND xp >= (level * 100)
            """)
    void tryLevelUp(@Param("id") UUID id);

    @ResultMap(responseMapper)
    @Delete("""
             DELETE FROM app_users WHERE app_user_id = #{id}  ;
            """)
    void deleteUserProfile(UUID id);
}
