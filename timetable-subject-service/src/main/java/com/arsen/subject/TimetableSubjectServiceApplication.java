package com.arsen.subject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TimetableSubjectServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimetableSubjectServiceApplication.class, args);
	}

}
