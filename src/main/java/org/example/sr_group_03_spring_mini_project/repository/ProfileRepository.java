package org.example.sr_group_03_spring_mini_project.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
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
}
