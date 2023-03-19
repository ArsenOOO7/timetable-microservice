package com.arsen.timetable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TimetableTimetableServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimetableTimetableServiceApplication.class, args);
	}

}
