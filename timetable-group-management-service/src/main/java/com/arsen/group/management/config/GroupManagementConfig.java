package com.arsen.group.management.config;

import com.arsen.common.annotation.EnableCommon;
import com.arsen.group.management.dto.GroupLessonDto;
import com.arsen.group.management.event.GroupEventUpdate;
import com.arsen.group.management.service.GroupLessonService;
import com.arsen.group.management.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaServiceInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Configuration
@EnableRabbit
@EnableCommon
@EnableDiscoveryClient
@RequiredArgsConstructor
public class GroupManagementConfig implements WebMvcConfigurer {

    private final GroupService groupService;
    private final GroupLessonService groupLessonService;
    private final DiscoveryClient discoveryClient;

    @Bean
    public Consumer<GroupEventUpdate> updateGroupEvent() {
        return groupService::synchronizeWithGroups;
    }

    @Bean
    public Consumer<List<GroupLessonDto>> updateTimetableEvent(){
        return groupLessonService::create;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        List<ServiceInstance> instances = discoveryClient.getInstances("timetable");
        String[] hosts = instances.stream().map(instance -> (EurekaServiceInstance) instance)
                .map(instance -> instance.getInstanceInfo().getIPAddr() + ":" + instance.getInstanceInfo().getPort())
                .toArray(String[]::new);

        registry.addMapping("/**")
                .allowedOrigins(hosts);

    }
}