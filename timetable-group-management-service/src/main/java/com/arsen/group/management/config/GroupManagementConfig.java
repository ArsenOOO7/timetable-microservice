package com.arsen.group.management.config;

import com.arsen.group.management.event.GroupEventUpdate;
import com.arsen.group.management.service.GroupReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@EnableRabbit
@RequiredArgsConstructor
public class GroupManagementConfig {

    private final GroupReadService groupReadService;

    @Bean
    public Consumer<GroupEventUpdate> updateGroupEvent() {
        return groupReadService::synchronizeWithGroups;
    }
}