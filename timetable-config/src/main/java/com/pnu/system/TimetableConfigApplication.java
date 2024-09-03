package com.pnu.system;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableAutoConfiguration
@EnableConfigServer
public class TimetableConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimetableConfigApplication.class, args);
    }
}
