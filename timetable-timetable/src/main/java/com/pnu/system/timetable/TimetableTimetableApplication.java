package com.pnu.system.timetable;

import com.pnu.system.common.AbstractTimetableApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimetableTimetableApplication extends AbstractTimetableApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimetableTimetableApplication.class, args);
    }

}