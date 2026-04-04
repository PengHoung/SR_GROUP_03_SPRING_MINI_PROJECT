package org.example.sr_group_03_spring_mini_project.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.sr_group_03_spring_mini_project.model.value.STATUSENUM;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitLogRequest {
    private STATUSENUM status;
    private String habitId;
}
