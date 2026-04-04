package org.example.sr_group_03_spring_mini_project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.sr_group_03_spring_mini_project.model.response.AppUserResponse;
import org.example.sr_group_03_spring_mini_project.model.value.FREQUENCYENUM;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Habit {

    private UUID habitId;
    private String title;
    private String description;
    private FREQUENCYENUM frequency;
    private Boolean isActive;
    private AppUserResponse appUserResponse;
    private LocalDate createAt;


}
