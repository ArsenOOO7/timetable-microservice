package com.pnu.system.common.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@ComponentScan({"com.pnu.system.common.service",
        "com.pnu.system.common.utils",
        "com.pnu.system.common.security",
        "com.pnu.system.common.rest",
        "com.pnu.system.common.snapshot",
        "com.pnu.system.common.messaging",
        "com.pnu.system.common.exception"})
@EnableJpaAuditing(auditorAwareRef = "timetableAuditorAware", dateTimeProviderRef = "zonedDateTimeProvider")
public class TimetableCommonConfiguration {

}
