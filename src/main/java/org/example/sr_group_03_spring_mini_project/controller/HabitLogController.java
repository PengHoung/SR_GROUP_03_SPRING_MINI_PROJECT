package org.example.sr_group_03_spring_mini_project.controller;

import lombok.RequiredArgsConstructor;
import org.example.sr_group_03_spring_mini_project.model.entity.HabitLogEntity;
import org.example.sr_group_03_spring_mini_project.model.request.HabitLogRequest;
import org.example.sr_group_03_spring_mini_project.model.response.ApiResponse;
import org.example.sr_group_03_spring_mini_project.service.HabitLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/habit-logs")
@RequiredArgsConstructor
public class HabitLogController {

    private final HabitLogService habitLogService;

    @PostMapping
    public ResponseEntity<ApiResponse<HabitLogEntity>> createHabitLog(
            @RequestBody HabitLogRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        UUID appUserId = UUID.fromString(userDetails.getUsername());
        HabitLogEntity log = habitLogService.createHabitLog(request, appUserId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Habit log created successfully!",
                        HttpStatus.CREATED,
                        log
                ));
    }

    @GetMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<List<HabitLogEntity>>> getHabitLogsByHabitId(
            @PathVariable("habit-id") UUID habitId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal UserDetails userDetails) {
        UUID appUserId = UUID.fromString(userDetails.getUsername());
        List<HabitLogEntity> logs = habitLogService.getHabitLogsByHabitId(habitId, appUserId, page, size);
        return ResponseEntity.ok(ApiResponse.success(
                "Fetched all habit logs for the specified habit ID successfully!",
                HttpStatus.OK,
                logs
        ));
    }
}