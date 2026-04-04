package org.example.sr_group_03_spring_mini_project.service;

import org.example.sr_group_03_spring_mini_project.model.entity.HabitLogEntity;
import org.example.sr_group_03_spring_mini_project.model.request.HabitLogRequest;

import java.util.List;
import java.util.UUID;
public interface HabitLogService {
    HabitLogEntity createHabitLog(HabitLogRequest request, UUID appUserId);
    List<HabitLogEntity> getHabitLogsByHabitId(UUID habitId, UUID appUserId, int page, int size);
}
//hello