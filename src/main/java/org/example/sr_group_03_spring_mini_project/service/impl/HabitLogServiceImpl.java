package org.example.sr_group_03_spring_mini_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sr_group_03_spring_mini_project.model.entity.Habit;
import org.example.sr_group_03_spring_mini_project.model.entity.HabitLog;
import org.example.sr_group_03_spring_mini_project.model.request.HabitLogRequest;
import org.example.sr_group_03_spring_mini_project.model.value.FrequencyPeriod;
import org.example.sr_group_03_spring_mini_project.model.value.STATUSENUM;
import org.example.sr_group_03_spring_mini_project.repository.HabitLogRepository;
import org.example.sr_group_03_spring_mini_project.repository.HabitRepository;
import org.example.sr_group_03_spring_mini_project.repository.ProfileRepository;
import org.example.sr_group_03_spring_mini_project.service.AchievementService;
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
    private final ProfileRepository profileRepository;
    private final AchievementService achievementService;

    @Override
    public HabitLog createHabitLog(HabitLogRequest request, UUID appUserId) {

        Habit habit = habitRepository
                .getHabitById(UUID.fromString(request.getHabitId()), appUserId)
                .orElseThrow(() -> new RuntimeException("Habit not found: " + request.getHabitId()));


        markMissedForUnloggedHabits(appUserId, habit.getHabitId());

        HabitLog log = new HabitLog();
        log.setHabitLogId(UUID.randomUUID());
        log.setLogDate(Instant.now());
        log.setStatus(request.getStatus().name());
        log.setXpEarned(calculateXp(request.getStatus()));
        log.setHabit(habit);

        HabitLog saved = habitLogRepository.save(log);
        saved.setHabit(habit);

        if (saved.getXpEarned() > 0) {
            profileRepository.addXpToUser(appUserId, saved.getXpEarned());
            profileRepository.tryLevelUp(appUserId);
        }

        achievementService.evaluateAndGrantAchievements(appUserId);

        Habit freshHabit = habitRepository
                .getHabitById(habit.getHabitId(), appUserId)
                .orElseThrow();
        saved.setHabit(freshHabit);

        return saved;
    }

    @Override
    public List<HabitLog> getHabitLogsByHabitId(UUID habitId, UUID appUserId, int page, int size) {
        Habit habit = habitRepository.getHabitById(habitId, appUserId)
                .orElseThrow(() -> new RuntimeException("Habit not found with id: " + habitId));

        int offset = (page - 1) * size;
        List<HabitLog> logs = habitLogRepository.findByHabitId(habitId, size, offset);
        logs.forEach(log -> log.setHabit(habit));

        return logs;
    }

    private Integer calculateXp(STATUSENUM status) {
        return switch (status) {
            case COMPLETED -> 10;
            case MISSED -> 0;
        };
    }

    private void markMissedForUnloggedHabits(UUID appUserId, UUID currentHabitId) {
        List<Habit> allHabits = habitRepository.getAllHabits(appUserId, 0, Integer.MAX_VALUE);

        for (Habit habit : allHabits) {
            if (habit.getHabitId().equals(currentHabitId)) continue;

            FrequencyPeriod period = FrequencyPeriod.of(habit.getFrequency());

            List<HabitLog> logs = habitLogRepository.findByHabitIdAndDateRange(
                    habit.getHabitId(),
                    period.from(),
                    period.to()
            );

            if (period.hasNoLogsIn(logs)) {
                habitLogRepository.insertMissedLog(habit.getHabitId(), Instant.now());
            }
        }
    }
}