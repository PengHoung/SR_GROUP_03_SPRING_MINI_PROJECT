package org.example.sr_group_03_spring_mini_project.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.sr_group_03_spring_mini_project.enumData.FREQUENCYENUM;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private FREQUENCYENUM frequency;
}
