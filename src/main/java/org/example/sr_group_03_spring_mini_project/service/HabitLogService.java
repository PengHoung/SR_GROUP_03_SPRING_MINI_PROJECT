package org.example.sr_group_03_spring_mini_project.service;

import org.example.sr_group_03_spring_mini_project.model.entity.HabitLog;
import org.example.sr_group_03_spring_mini_project.model.request.HabitLogRequest;

import java.util.List;
import java.util.UUID;
public interface HabitLogService {
    HabitLog createHabitLog(HabitLogRequest request, UUID appUserId);
    List<HabitLog> getHabitLogsByHabitId(UUID habitId, UUID appUserId, int page, int size);
}