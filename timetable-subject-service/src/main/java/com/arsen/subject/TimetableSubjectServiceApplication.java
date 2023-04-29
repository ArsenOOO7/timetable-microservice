package com.arsen.subject;

import com.arsen.common.annotation.EnableCommon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCommon
public class TimetableSubjectServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimetableSubjectServiceApplication.class, args);
	}

}
