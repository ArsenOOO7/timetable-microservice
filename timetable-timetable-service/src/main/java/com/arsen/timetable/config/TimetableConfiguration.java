package com.arsen.timetable.config;

import com.arsen.common.annotation.EnableCommon;
import com.arsen.timetable.event.ClassroomUpdateEvent;
import com.arsen.timetable.event.SubjectUpdateEvent;
import com.arsen.timetable.event.TeacherUpdateEvent;
import com.arsen.timetable.filter.JwtVerifyFilter;
import com.arsen.timetable.service.readonly.ClassroomReadService;
import com.arsen.timetable.service.readonly.SubjectReadService;
import com.arsen.timetable.service.readonly.TeacherReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.function.Consumer;

@Configuration
@EnableRabbit
@EnableCommon
@EnableDiscoveryClient
@EnableMethodSecurity
@RequiredArgsConstructor
public class TimetableConfiguration {

    private final ClassroomReadService classroomReadService;
    private final TeacherReadService teacherReadService;
    private final SubjectReadService subjectReadService;
    private final JwtVerifyFilter jwtVerifyFilter;


    @Bean
    public Consumer<ClassroomUpdateEvent> classroomUpdateEventConsumer(){
        return classroomReadService::synchronize;
    }

    @Bean
    public Consumer<SubjectUpdateEvent> subjectUpdateEventConsumer(){
        return subjectReadService::synchronize;
    }

    @Bean
    public Consumer<TeacherUpdateEvent> teacherUpdateEventConsumer(){
        return teacherReadService::synchronize;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .addFilterBefore(jwtVerifyFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic()
                .and().build();
    }

}
