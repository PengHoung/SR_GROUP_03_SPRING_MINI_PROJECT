package org.example.sr_group_03_spring_mini_project.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.example.sr_group_03_spring_mini_project.model.entity.Habit;
import org.example.sr_group_03_spring_mini_project.model.request.HabitRequest;

import java.util.List;
import java.util.UUID;

public interface HabitService {
    List<Habit> getAllHabits(@Positive int page, @Positive int size);

    Habit getHabitById(UUID habitId);

    void deleteHabitById(UUID habitId);

    Habit createHabit(@Valid HabitRequest habitRequest);

    Habit updateHabitById(UUID habitId, @Valid HabitRequest habitRequest);
}
