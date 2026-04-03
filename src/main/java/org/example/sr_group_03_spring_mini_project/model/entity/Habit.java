package org.example.sr_group_03_spring_mini_project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.sr_group_03_spring_mini_project.enumData.FREQUENCYENUM;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Habit {

    private String habitId;
    private String title;
    private String description;
    private FREQUENCYENUM frequency;
    private Boolean isActive;
    private AppUser appUserResponse;
    private LocalDate createAt;


}
