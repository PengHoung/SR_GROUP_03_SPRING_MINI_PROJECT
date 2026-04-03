package org.example.sr_group_03_spring_mini_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/habit")
@RequiredArgsConstructor
public class HabitController {
    @GetMapping
    public String hello () {
        return "Hello World";
    }
}
