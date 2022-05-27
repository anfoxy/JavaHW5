package com.netcracker.config;

import com.netcracker.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public UserService action() {
        return new UserService();
    }
}
