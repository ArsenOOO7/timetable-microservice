package com.arsen.group.management.config;

import com.arsen.common.config.CommonConfig;
import com.arsen.group.management.dto.GroupLessonDto;
import com.arsen.group.management.event.GroupEventUpdate;
import com.arsen.group.management.service.GroupLessonService;
import com.arsen.group.management.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Configuration
@EnableRabbit
@Import(CommonConfig.class)
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