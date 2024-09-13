package com.pnu.system.identityaccess;

import com.pnu.system.common.config.TimetableCommonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(TimetableCommonConfiguration.class)
@SpringBootApplication
public class TimetableIdentityAccessApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimetableIdentityAccessApplication.class, args);
    }
}