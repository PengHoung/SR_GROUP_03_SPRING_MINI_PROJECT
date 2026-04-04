package org.example.sr_group_03_spring_mini_project.repository;

import org.apache.ibatis.annotations.*;
import org.example.sr_group_03_spring_mini_project.config.GenericEnumTypeHandler;
import org.example.sr_group_03_spring_mini_project.config.UuidTypeHandler;
import org.example.sr_group_03_spring_mini_project.model.entity.Habit;
import org.example.sr_group_03_spring_mini_project.model.request.HabitRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface HabitRepository {

    @Results(id = "habitMapper", value = {
            @Result(property = "habitId", column = "habit_id", typeHandler = UuidTypeHandler.class),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "frequency", column = "frequency", typeHandler = GenericEnumTypeHandler.class),
            @Result(property = "createdAt", column = "created_at"),
            @Result(
                    property = "appUserResponse",
                    column = "app_user_id",
                    one = @One(select = "org.example.sr_group_03_spring_mini_project.repository.AuthRepository.findById")
            ),
    })
    @Select("""
            SELECT * FROM habits WHERE app_user_id = #{appUserId} OFFSET #{offset} LIMIT #{size};
            """)
    List<Habit> getAllHabits(UUID appUserId, int offset, int size);


    @ResultMap("habitMapper")
    @Select("""
            SELECT * FROM habits WHERE habit_id = #{habitId} AND app_user_id = #{appUserId} ;
            """)
    Optional<Habit> getHabitById(UUID habitId, UUID appUserId);

    @Delete("""
            DELETE FROM habits WHERE habit_id = #{habitId} AND app_user_id = #{appUserId} ;
            """)
    void deleteHabitById(UUID habitId, UUID appUserId);

    @ResultMap("habitMapper")
    @Select("""
            INSERT INTO habits(title,description,frequency,app_user_id) VALUES(#{req.title},#{req.description},#{req.frequency}::frequency_enum,#{appUserId}) RETURNING *;;
            """)
    Habit createHabit(@Param("req") HabitRequest habitRequest, UUID appUserId);


    @ResultMap("habitMapper")
    @Select("""
            
                UPDATE habits SET title = #{req.title} , description = #{req.description} , frequency = #{req.frequency}::frequency_enum WHERE habit_id = #{habitId} AND app_user_id = #{appUserid} RETURNING * ;
            """)
    Habit updateHabitById(UUID habitId, UUID appUserid, @Param("req") HabitRequest habitRequest);
}
