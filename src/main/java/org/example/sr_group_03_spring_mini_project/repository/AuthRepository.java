package org.example.sr_group_03_spring_mini_project.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.example.sr_group_03_spring_mini_project.model.entity.AppUser;

@Mapper
public interface AuthRepository {
    String authMapper = "authMapper";

    @Results(id = authMapper, value = {
            @Result(property = AppUser.Fields.appUserId, column = "app_user_id", jdbcType = JdbcType.OTHER),
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

}
