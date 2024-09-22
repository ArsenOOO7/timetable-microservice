package com.pnu.system.academiccatalog.security;

import com.pnu.system.common.security.TimetableCommonWebSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

@Slf4j
@Configuration
public class AcademicCatalogSecurity extends TimetableCommonWebSecurityConfig {
    @Override
    protected void configureHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry
                .anyRequest().authenticated();
    }
}
