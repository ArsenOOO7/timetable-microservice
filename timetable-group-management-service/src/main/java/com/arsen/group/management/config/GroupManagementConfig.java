package com.arsen.group.management.config;

import com.arsen.common.config.CommonConfig;
import com.arsen.group.management.event.GroupEventUpdate;
import com.arsen.group.management.service.GroupReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.function.Consumer;

@Configuration
@EnableRabbit
@Import(CommonConfig.class)
@RequiredArgsConstructor
public class GroupManagementConfig {

    private final GroupReadService groupReadService;

    @Bean
    public Consumer<GroupEventUpdate> updateGroupEvent() {
        return groupReadService::synchronizeWithGroups;
    }
}