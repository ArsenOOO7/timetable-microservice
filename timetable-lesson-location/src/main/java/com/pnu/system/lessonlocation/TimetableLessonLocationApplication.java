package com.pnu.system.lessonlocation;

import com.pnu.system.common.AbstractTimetableApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimetableLessonLocationApplication extends AbstractTimetableApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimetableLessonLocationApplication.class, args);
    }

}