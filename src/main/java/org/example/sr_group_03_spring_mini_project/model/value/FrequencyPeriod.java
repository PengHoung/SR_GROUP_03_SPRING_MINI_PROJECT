package org.example.sr_group_03_spring_mini_project.model.value;


import org.example.sr_group_03_spring_mini_project.model.entity.HabitLog;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public record FrequencyPeriod(LocalDate from, LocalDate to) {

    public static FrequencyPeriod of(FREQUENCYENUM frequency) {
        LocalDate today = LocalDate.now();
        return switch (frequency) {
            case FREQUENCYENUM.DAILY -> new FrequencyPeriod(today, today);
            case FREQUENCYENUM.WEEKLY -> new FrequencyPeriod(
                    today.with(DayOfWeek.MONDAY),
                    today.with(DayOfWeek.SUNDAY)
            );
            case FREQUENCYENUM.MONTHLY -> new FrequencyPeriod(
                    today.with(TemporalAdjusters.firstDayOfMonth()),
                    today.with(TemporalAdjusters.lastDayOfMonth())
            );
        };
    }

    private LocalDate toLocalDate(Instant instant) {
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public boolean hasNoLogsIn(List<HabitLog> logs) {
        return logs.stream().noneMatch(log -> {
            LocalDate logDate = toLocalDate(log.getLogDate());
            return !logDate.isBefore(from) && !logDate.isAfter(to);
        });
    }


}
