package com.arsen.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "com.arsen.common", excludeFilters = @ComponentScan.Filter(Configuration.class))
public class CommonConfig {
}
