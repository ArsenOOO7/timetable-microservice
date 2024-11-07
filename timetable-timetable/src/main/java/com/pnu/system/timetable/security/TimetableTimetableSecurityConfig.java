package com.pnu.system.timetable.security;

import com.pnu.system.common.constant.PermissionName;
import com.pnu.system.common.security.TimetableCommonWebSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

@Configuration
public class TimetableTimetableSecurityConfig extends TimetableCommonWebSecurityConfig {

    @Override
    protected void configureHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry
                .requestMatchers("/lesson/list/group", "/lesson/list/location", "/lesson/list/teacher").permitAll()
                .requestMatchers("/lesson/list").authenticated()
                .requestMatchers("/**/internal/**").hasAuthority(PermissionName.INTERNAL_USE.name())
                .requestMatchers("/lesson/type/*").hasAuthority(PermissionName.LESSON_TYPE_EDIT.name())
                .requestMatchers("/lesson/*").hasAuthority(PermissionName.LESSON_EDIT.name())
                .anyRequest().authenticated();
    }
}
