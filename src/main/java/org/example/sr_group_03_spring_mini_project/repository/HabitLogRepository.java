package org.example.sr_group_03_spring_mini_project.repository;

import org.apache.ibatis.annotations.*;
import org.example.sr_group_03_spring_mini_project.config.UuidTypeHandler;
import org.example.sr_group_03_spring_mini_project.model.entity.HabitLog;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitLogRepository {

    @Results(id = "habitLogMapper", value = {
            @Result(property = "habitLogId",    column = "habit_log_id", typeHandler = UuidTypeHandler.class),
            @Result(property = "logDate",       column = "log_date"),
            @Result(property = "status",        column = "status"),
            @Result(property = "xpEarned",      column = "xp_earned"),
            @Result(property = "habit.habitId", column = "habit_id", typeHandler = UuidTypeHandler.class)
    })
    @Insert("""
        INSERT INTO habit_logs (habit_log_id, log_date, status, xp_earned, habit_id)
        VALUES (
            #{habitLogId, typeHandler=org.example.sr_group_03_spring_mini_project.config.UuidTypeHandler},
            #{logDate},
            #{status},
            #{xpEarned},
            #{habit.habitId, typeHandler=org.example.sr_group_03_spring_mini_project.config.UuidTypeHandler}
        )
        RETURNING *
    """)
    HabitLog save(HabitLog habitLog);

    @ResultMap("habitLogMapper")
    @Select("""
        SELECT habit_log_id, log_date, status, xp_earned, habit_id
        FROM habit_logs
        WHERE habit_id = #{habitId, typeHandler=org.example.sr_group_03_spring_mini_project.config.UuidTypeHandler}
        ORDER BY log_date DESC
        LIMIT #{size} OFFSET #{offset}
    """)
    List<HabitLog> findByHabitId(
            @Param("habitId") UUID habitId,
            @Param("size") int size,
            @Param("offset") int offset);
}