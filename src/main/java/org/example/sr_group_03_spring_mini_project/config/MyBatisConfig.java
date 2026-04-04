package org.example.sr_group_03_spring_mini_project.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {

    @Bean
    public ConfigurationCustomizer myBatisCustomizer() {
        return configuration -> {
            configuration.getTypeHandlerRegistry().register(
                    java.util.UUID.class,
                    UuidTypeHandler.class
            );
        };
    }
}
