package org.example.sr_group_03_spring_mini_project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HabitLog {
    private UUID habitLogId;
    private Instant logDate;
    private String status;
    private Integer xpEarned;
    private Habit habit;
}