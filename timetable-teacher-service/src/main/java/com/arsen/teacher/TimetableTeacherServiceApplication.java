package com.arsen.teacher;

import com.arsen.common.annotation.EnableCommon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableCommon(elastic = true)
@EnableDiscoveryClient
public class TimetableTeacherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimetableTeacherServiceApplication.class, args);
	}

}
