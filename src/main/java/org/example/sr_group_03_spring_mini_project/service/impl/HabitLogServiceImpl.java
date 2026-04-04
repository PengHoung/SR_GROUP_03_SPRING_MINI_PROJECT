package org.example.sr_group_03_spring_mini_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sr_group_03_spring_mini_project.enumData.STATUSENUM;
import org.example.sr_group_03_spring_mini_project.model.entity.Habit;
import org.example.sr_group_03_spring_mini_project.model.entity.HabitLogEntity;
import org.example.sr_group_03_spring_mini_project.model.request.HabitLogRequest;
import org.example.sr_group_03_spring_mini_project.repository.HabitLogRepository;
import org.example.sr_group_03_spring_mini_project.repository.HabitRepository;
import org.example.sr_group_03_spring_mini_project.service.HabitLogService;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitLogServiceImpl implements HabitLogService {

    private final HabitLogRepository habitLogRepository;
    private final HabitRepository habitRepository;

    @Override
    public HabitLogEntity createHabitLog(HabitLogRequest request, UUID appUserId) {
        Habit habit = habitRepository.getHabitById(
                        UUID.fromString(request.getHabitId()), appUserId)
                .orElseThrow(() -> new RuntimeException("Habit not found with id: " + request.getHabitId()));

        HabitLogEntity log = new HabitLogEntity();
        log.setHabitLogId(UUID.randomUUID());
        log.setLogDate(Instant.now());
        log.setStatus(request.getStatus().name());
        log.setXpEarned(calculateXp(request.getStatus()));
        log.setHabit(habit);

        HabitLogEntity saved = habitLogRepository.save(log);
        saved.setHabit(habit);
        return saved;
    }

    @Override
    public List<HabitLogEntity> getHabitLogsByHabitId(UUID habitId, UUID appUserId, int page, int size) {
        Habit habit = habitRepository.getHabitById(habitId, appUserId)
                .orElseThrow(() -> new RuntimeException("Habit not found with id: " + habitId));

        int offset = (page - 1) * size;
        List<HabitLogEntity> logs = habitLogRepository.findByHabitId(habitId, size, offset);
        logs.forEach(log -> log.setHabit(habit));

        return logs;
    }
    private Integer calculateXp(STATUSENUM status) {
        return switch (status) {
            case COMPLETED -> 10;
            case MISSED -> 0;
        };
    }
}