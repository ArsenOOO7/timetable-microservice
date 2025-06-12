package com.pnu.system.laboratorycontrol;

import com.pnu.system.common.AbstractTimetableApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimetableLaboratoryControlApplication extends AbstractTimetableApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimetableLaboratoryControlApplication.class, args);
    }

}
