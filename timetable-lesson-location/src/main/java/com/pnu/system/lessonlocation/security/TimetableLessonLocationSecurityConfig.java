package com.pnu.system.lessonlocation.security;

import com.pnu.system.common.constant.PermissionName;
import com.pnu.system.common.security.TimetableCommonWebSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

@Slf4j
@Configuration
@EnableWebSecurity
public class TimetableLessonLocationSecurityConfig extends TimetableCommonWebSecurityConfig {

    @Override
    protected void configreHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry
                .requestMatchers("/location/**").hasAnyAuthority(PermissionName.LESSON_LOCATION_EDIT.name())
                .anyRequest().authenticated();
    }
}