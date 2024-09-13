package com.pnu.system.identityaccess.security;

import com.pnu.system.common.secutity.TimetableCommonWebSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

@Slf4j
@Configuration
@EnableWebSecurity
public class TimetableIdentityAccessConfig extends TimetableCommonWebSecurityConfig {

    @Override
    protected void configreHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry
                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                .anyRequest().authenticated();
    }
}