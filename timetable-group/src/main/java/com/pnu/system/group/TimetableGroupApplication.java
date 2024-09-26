package com.pnu.system.group;

import com.pnu.system.common.AbstractTimetableApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimetableGroupApplication extends AbstractTimetableApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimetableGroupApplication.class, args);
    }
}