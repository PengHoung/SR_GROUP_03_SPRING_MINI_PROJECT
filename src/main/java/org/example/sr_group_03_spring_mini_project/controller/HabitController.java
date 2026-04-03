package org.example.sr_group_03_spring_mini_project.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.sr_group_03_spring_mini_project.model.entity.Habit;
import org.example.sr_group_03_spring_mini_project.model.request.HabitRequest;
import org.example.sr_group_03_spring_mini_project.model.response.ApiResponse;
import org.example.sr_group_03_spring_mini_project.service.HabitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/habit")
@RequiredArgsConstructor
@Validated
public class HabitController {
    private final HabitService habitService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Habit>>> getAllHabits (@RequestParam(defaultValue = "1") @Positive int page , @RequestParam(defaultValue = "10") @Positive int size) {
        List<Habit> habits = habitService.getAllHabits(page , size);
        return ResponseEntity.ok(ApiResponse.success("Fetched all habits successfully!", HttpStatus.OK,habits));
    }
    @GetMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> getHabitById (@PathVariable(name="habit-id") UUID habitId) {
        Habit habit = habitService.getHabitById(habitId);
        return ResponseEntity.ok(ApiResponse.success("Fetched habit ID successfully!", HttpStatus.OK,habit));
    }
    @DeleteMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<Void>> deleteHabitById (@PathVariable(name="habit-id") UUID habitId) {
         habitService.deleteHabitById(habitId);
        return ResponseEntity.ok(ApiResponse.success("Habit with this ID Delete successfully!", HttpStatus.OK,null));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Habit>> createHabit (@Valid @RequestBody HabitRequest habitRequest) {
            Habit habit = habitService.createHabit(habitRequest);
            return ResponseEntity.ok(ApiResponse.success("Habit create successfully!", HttpStatus.CREATED,habit));
    }
    @PutMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> updateHabitById (@PathVariable(name="habit-id") UUID habitId, @Valid @RequestBody HabitRequest habitRequest) {
        Habit habit = habitService.updateHabitById(habitId,habitRequest);
        return ResponseEntity.ok(ApiResponse.success("Update habit successfully!", HttpStatus.OK,habit));
    }
}
