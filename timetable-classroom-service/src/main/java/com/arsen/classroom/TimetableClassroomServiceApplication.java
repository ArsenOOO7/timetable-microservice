package com.arsen.classroom;

import com.arsen.common.annotation.EnableCommon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableCommon
@EnableDiscoveryClient
public class TimetableClassroomServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimetableClassroomServiceApplication.class, args);
	}

}
