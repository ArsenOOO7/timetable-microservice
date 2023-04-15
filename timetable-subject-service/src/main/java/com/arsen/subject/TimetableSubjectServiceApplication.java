package com.arsen.subject;

import com.arsen.common.config.CommonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@Import(CommonConfig.class)
public class TimetableSubjectServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimetableSubjectServiceApplication.class, args);
	}

}
