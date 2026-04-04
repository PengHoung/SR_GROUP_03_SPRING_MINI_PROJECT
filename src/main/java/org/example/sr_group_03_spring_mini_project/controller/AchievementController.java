package org.example.sr_group_03_spring_mini_project.controller;

import lombok.RequiredArgsConstructor;
import org.example.sr_group_03_spring_mini_project.model.entity.Achievement;
import org.example.sr_group_03_spring_mini_project.model.response.ApiResponse;
import org.example.sr_group_03_spring_mini_project.service.AchievementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/v1/achievements")
@RequiredArgsConstructor
public class AchievementController {

    private final AchievementService achievementService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Achievement>>> getAllAchievements(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        List<Achievement> achievements = achievementService.getAllAchievements(page,size);
        return ResponseEntity.ok(
                ApiResponse.<List<Achievement>>builder()
                        .timestamp(Instant.now())
                        .message("Achievements retrieved successfully!")
                        .status("OK")
                        .success(true)
                        .payload(achievements)
                        .build()
        );
    }

    @GetMapping("/app-users")
    public ResponseEntity<ApiResponse<List<Achievement>>> getCurrentUserAchievements(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        List<Achievement> achievements = achievementService.getCurrentUserAchievements(page, size);
        return ResponseEntity.ok(
                ApiResponse.<List<Achievement>>builder()
                        .timestamp(Instant.now())
                        .message("Achievements for the specified App User retrieved successfully!")
                        .status("OK")
                        .success(true)
                        .payload(achievements)
                        .build()
        );
    }
}
