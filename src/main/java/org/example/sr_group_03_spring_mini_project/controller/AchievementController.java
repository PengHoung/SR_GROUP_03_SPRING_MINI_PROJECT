package org.example.sr_group_03_spring_mini_project.controller;

import org.example.sr_group_03_spring_mini_project.model.entity.Achievement;
import org.example.sr_group_03_spring_mini_project.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/achievements")
public class AchievementController {
    public ResponseEntity<List<Achievement>> getAllAchievement(){
        return null;
    }
}
