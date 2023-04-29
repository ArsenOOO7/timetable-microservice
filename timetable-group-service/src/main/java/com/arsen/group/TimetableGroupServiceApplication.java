package com.arsen.group;

import com.arsen.common.annotation.EnableCommon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableCommon
@EnableDiscoveryClient
public class TimetableGroupServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimetableGroupServiceApplication.class, args);
	}

}
