package org.example.sr_group_03_spring_mini_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sr_group_03_spring_mini_project.exception.ApiException;
import org.example.sr_group_03_spring_mini_project.exception.auth.InvalidCredentialsException;
import org.example.sr_group_03_spring_mini_project.exception.auth.UserNotFoundException;
import org.example.sr_group_03_spring_mini_project.model.entity.AppUser;
import org.example.sr_group_03_spring_mini_project.model.entity.Habit;
import org.example.sr_group_03_spring_mini_project.model.request.HabitRequest;
import org.example.sr_group_03_spring_mini_project.repository.HabitRepository;
import org.example.sr_group_03_spring_mini_project.service.HabitService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;

    @Override
    public List<Habit> getAllHabits(int page, int size) {
        AppUser appUser = CheckIsAuthenticated();
        int offset = (page - 1) * size;
        return habitRepository.getAllHabits(appUser.getAppUserId(), offset, size);
    }

    @Override
    public Habit getHabitById(UUID habitId) {
        AppUser appUser = CheckIsAuthenticated();
        return habitRepository.getHabitById(habitId, appUser.getAppUserId()).orElseThrow(() -> ApiException.notFound("Habit with this id " + habitId + " not found"));
    }

    @Override
    public void deleteHabitById(UUID habitId) {
        AppUser appUser = CheckIsAuthenticated();
        getHabitById(habitId);
        habitRepository.deleteHabitById(habitId, appUser.getAppUserId());
    }

    @Override
    public Habit createHabit(HabitRequest habitRequest) {
        AppUser appUser = CheckIsAuthenticated();

        return habitRepository.createHabit(habitRequest, appUser.getAppUserId());
    }

    @Override
    public Habit updateHabitById(UUID habitId, HabitRequest habitRequest) {
        AppUser appUser = CheckIsAuthenticated();
        getHabitById(habitId);
        return habitRepository.updateHabitById(habitId, appUser.getAppUserId(), habitRequest);
    }

    private AppUser CheckIsAuthenticated() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new InvalidCredentialsException();
        }
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (appUser == null) {
            throw new UserNotFoundException();
        }
        return appUser;
    }
}
