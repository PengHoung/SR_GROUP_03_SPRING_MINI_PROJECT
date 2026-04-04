package org.example.sr_group_03_spring_mini_project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.sr_group_03_spring_mini_project.model.response.AppUserResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    private AppUserResponse appUserResponse;
}
