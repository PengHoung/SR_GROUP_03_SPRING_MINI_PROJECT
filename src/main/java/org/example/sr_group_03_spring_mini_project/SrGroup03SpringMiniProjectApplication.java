package org.example.sr_group_03_spring_mini_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
public class SrGroup03SpringMiniProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrGroup03SpringMiniProjectApplication.class, args);
    }

}
