package com.pnu.system.lessonlocation;

import com.pnu.system.common.config.TimetableCommonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(TimetableCommonConfiguration.class)
@SpringBootApplication
public class TimetableLessonLocationApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimetableLessonLocationApplication.class, args);
    }
}