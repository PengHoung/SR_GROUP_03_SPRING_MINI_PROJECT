package org.example.sr_group_03_spring_mini_project.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.sr_group_03_spring_mini_project.enumData.FREQUENCYENUM;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitRequest {
private String title;
private String description;
private FREQUENCYENUM frequency;
}
