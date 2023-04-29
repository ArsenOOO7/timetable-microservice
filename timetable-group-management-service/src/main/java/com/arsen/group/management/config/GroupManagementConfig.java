package com.arsen.group.management.config;

import com.arsen.common.annotation.EnableCommon;
import com.arsen.group.management.dto.GroupLessonDto;
import com.arsen.group.management.event.GroupEventUpdate;
import com.arsen.group.management.service.GroupLessonService;
import com.arsen.group.management.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Consumer;

@Configuration
@EnableRabbit
@EnableCommon
@EnableDiscoveryClient
@RequiredArgsConstructor
public class GroupManagementConfig {

    private final GroupService groupService;
    private final GroupLessonService groupLessonService;

    @Bean
    public Consumer<GroupEventUpdate> updateGroupEvent() {
        return groupService::synchronizeWithGroups;
    }

    @Bean
    public Consumer<List<GroupLessonDto>> updateTimetableEvent(){
        return groupLessonService::create;
    }
}