package com.pnu.system.identityaccess;

import com.pnu.system.common.AbstractTimetableApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimetableIdentityAccessApplication extends AbstractTimetableApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimetableIdentityAccessApplication.class, args);
    }

}