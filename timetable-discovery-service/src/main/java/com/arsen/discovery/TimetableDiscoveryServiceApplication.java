package com.arsen.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TimetableDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimetableDiscoveryServiceApplication.class, args);
	}

}
