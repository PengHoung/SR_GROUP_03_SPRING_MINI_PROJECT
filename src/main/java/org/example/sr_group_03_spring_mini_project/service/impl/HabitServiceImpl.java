package org.example.sr_group_03_spring_mini_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sr_group_03_spring_mini_project.exception.ApiException;
import org.example.sr_group_03_spring_mini_project.model.entity.Habit;
import org.example.sr_group_03_spring_mini_project.model.request.HabitRequest;
import org.example.sr_group_03_spring_mini_project.repository.HabitRepository;
import org.example.sr_group_03_spring_mini_project.service.HabitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {
    private final HabitRepository habitRepository;
    UUID appUserId = UUID.fromString("f18a9fc4-0821-409e-b942-de7f4f301759");
    @Override
    public List<Habit> getAllHabits(int page, int size) {
        int offset = (page - 1) * size;
        return habitRepository.getAllHabits(appUserId,offset,size);
    }

    @Override
    public Habit getHabitById(UUID habitId) {
        return habitRepository.getHabitById(habitId,appUserId).orElseThrow(()-> ApiException.notFound("Habit with this id " + habitId +" not found"));
    }

    @Override
    public void deleteHabitById(UUID habitId) {
        getHabitById(habitId);
        habitRepository.deleteHabitById(habitId,appUserId);
    }

    @Override
    public Habit createHabit(HabitRequest habitRequest) {
        return habitRepository.createHabit(habitRequest,appUserId);
    }

    @Override
    public Habit updateHabitById(UUID habitId, HabitRequest habitRequest) {
        getHabitById(habitId);
        return habitRepository.updateHabitById(habitId,appUserId,habitRequest);
    }

}
