package org.example.sr_group_03_spring_mini_project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.sr_group_03_spring_mini_project.enumData.STATUSENUM;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitLog {
    private String habitLogId;
    private LocalDate logDate;
    private STATUSENUM status;
    private Long xpEarned;
    private Habit habit;
}
