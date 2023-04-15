package com.arsen.group;

import com.arsen.common.config.CommonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CommonConfig.class)
public class TimetableGroupServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimetableGroupServiceApplication.class, args);
	}

}
