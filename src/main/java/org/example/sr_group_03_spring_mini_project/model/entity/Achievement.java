package org.example.sr_group_03_spring_mini_project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Achievement {
    private String achievementId;
    private String title;
    private String description;
    private String badge;
    private Long xp_required;
}
