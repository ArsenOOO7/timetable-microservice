package com.arsen.timetable.config;

import com.arsen.timetable.event.ClassroomUpdateEvent;
import com.arsen.timetable.event.SubjectUpdateEvent;
import com.arsen.timetable.event.TeacherUpdateEvent;
import com.arsen.timetable.service.readonly.ClassroomReadService;
import com.arsen.timetable.service.readonly.SubjectReadService;
import com.arsen.timetable.service.readonly.TeacherReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@EnableRabbit
@RequiredArgsConstructor
public class TimetableConfiguration {

    private final ClassroomReadService classroomReadService;
    private final TeacherReadService teacherReadService;
    private final SubjectReadService subjectReadService;


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

}
