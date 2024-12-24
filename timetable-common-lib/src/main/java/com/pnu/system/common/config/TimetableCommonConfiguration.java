package com.pnu.system.common.config;


import org.elasticsearch.client.sniff.Sniffer;
import org.springframework.context.annotation.Bean;
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
        "com.pnu.system.common.search"})
@EnableJpaAuditing(auditorAwareRef = "timetableAuditorAware", dateTimeProviderRef = "zonedDateTimeProvider")
public class TimetableCommonConfiguration {

    /*
    TODO 6/14/24: Replace with good fix, maybe...
     U need to override with the help of Sniffer builder. If u do this, I'll buy any ice-cream for being such clever boy/girl :)
     */
    @Bean
    public Sniffer sniffer() {
        return null;
    }
}
