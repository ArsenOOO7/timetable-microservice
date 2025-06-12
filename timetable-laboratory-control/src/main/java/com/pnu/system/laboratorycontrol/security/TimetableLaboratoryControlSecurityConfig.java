package com.pnu.system.laboratorycontrol.security;

import com.pnu.system.common.constant.PermissionName;
import com.pnu.system.common.security.TimetableCommonWebSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

import static com.pnu.system.common.constant.PermissionName.COURSE_FULL_ACCESS;

@Configuration
public class TimetableLaboratoryControlSecurityConfig extends TimetableCommonWebSecurityConfig {

    @Override
    protected void configureHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry
                .requestMatchers("/course/**").hasAnyAuthority(PermissionName.COURSE_EDIT.name(), COURSE_FULL_ACCESS.name())
                .anyRequest().authenticated();
    }
}
