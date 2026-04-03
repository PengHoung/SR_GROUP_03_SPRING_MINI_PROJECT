package org.example.sr_group_03_spring_mini_project.repository;

import org.apache.ibatis.annotations.*;
import org.example.sr_group_03_spring_mini_project.config.GenericEnumTypeHandler;
import org.example.sr_group_03_spring_mini_project.config.UuidTypeHandler;
import org.example.sr_group_03_spring_mini_project.model.entity.Habit;
import org.example.sr_group_03_spring_mini_project.model.request.HabitRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface HabitRepository {

    @Results(id = "habitMapper", value = {
            @Result(property = "habitId", column = "habit_id",typeHandler = UuidTypeHandler.class),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "frequency", column = "frequency" , typeHandler = GenericEnumTypeHandler.class),
//            @Result(property = "appUserResponse", column = "app_user_id" , typeHandler = UuidTypeHandler.class),
            @Result(property = "createdAt", column = "created_at"),
    })
    @Select("""
select * from habits where app_user_id = #{appUserId} offset #{offset} limit #{size};
""")
    List<Habit> getAllHabits(UUID appUserId, int offset, int size);


    @ResultMap("habitMapper")
    @Select("""
select * from habits where habit_id = #{habitId} and app_user_id = #{appUserId} ;
""")
    Optional<Habit> getHabitById(UUID habitId,UUID appUserId);

    @Delete("""
delete from habits where habit_id = #{habitId} and app_user_id = #{appUserId} ;
""")
    void deleteHabitById(UUID habitId, UUID appUserId);

    @ResultMap("habitMapper")
    @Select("""
insert into habits(title,description,frequency,app_user_id) values(#{req.title},#{req.description},#{req.frequency}::frequency_enum,#{appUserId}) returning *;;
""")
    Habit createHabit(@Param("req") HabitRequest habitRequest, UUID appUserId);


    @ResultMap("habitMapper")
    @Select("""

    update habits set title = #{req.title} , description = #{req.description} , frequency = #{req.frequency}::frequency_enum where habit_id = #{habitId} and app_user_id = #{appUserid} returning * ;
""")
    Habit updateHabitById(UUID habitId,UUID appUserid,@Param("req") HabitRequest habitRequest);
}
