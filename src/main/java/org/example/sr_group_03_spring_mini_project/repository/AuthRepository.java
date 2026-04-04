package org.example.sr_group_03_spring_mini_project.repository;

import org.apache.ibatis.annotations.*;
import org.example.sr_group_03_spring_mini_project.config.UuidTypeHandler;
import org.example.sr_group_03_spring_mini_project.model.entity.AppUser;

import java.util.UUID;

@Mapper
public interface AuthRepository {
    String authMapper = "authMapper";

    @Results(id = authMapper, value = {
            @Result(property = AppUser.Fields.appUserId, column = "app_user_id"),
            @Result(property = AppUser.Fields.userName, column = "username"),
            @Result(property = AppUser.Fields.email, column = "email"),
            @Result(property = AppUser.Fields.level, column = "level"),
            @Result(property = AppUser.Fields.xp, column = "xp"),
            @Result(property = AppUser.Fields.profileImage, column = "profile_image"),
            @Result(property = AppUser.Fields.isVerified, column = "is_verified"),
            @Result(property = AppUser.Fields.createAt, column = "created_at"),

    })
    @Select("""
                SELECT * FROM app_users
                WHERE email = #{identifier}
                   OR username = #{identifier}
                LIMIT 1
            """)
    AppUser getAppUserByIdentifier(String identifier);

    @ResultMap(authMapper)
    @Select("SELECT * FROM app_users WHERE app_user_id = #{id}")
    AppUser findById(UUID id);

    @Select("SELECT COUNT(1) FROM app_users WHERE email = #{email} OR username=#{username}")
    boolean existsByEmailOrUsername(String email, String username);


    @ResultMap(authMapper)
    @Select("""
            INSERT INTO app_users (app_user_id, username, email, password, level, xp, profile_image, is_verified, created_at)
            VALUES (DEFAULT, #{userName}, #{email}, #{password}, DEFAULT, DEFAULT, #{profileImage}, DEFAULT, DEFAULT)
            RETURNING *;
            """)
    AppUser saveUser(AppUser user);

    @Update("UPDATE app_users SET is_verified = TRUE WHERE email = #{email}")
    void verifyUserByEmail(String email);
}
