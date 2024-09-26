package com.pnu.system.group.security;

import com.pnu.system.common.constant.PermissionName;
import com.pnu.system.common.security.TimetableCommonWebSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

@Configuration
public class TimetableGroupWebSecurityConfig extends TimetableCommonWebSecurityConfig {

    @Override
    protected void configureHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry
                .requestMatchers("/group/category/**").hasAuthority(PermissionName.GROUP_CATEGORY_EDIT.name())
                .requestMatchers("/group/**").hasAuthority(PermissionName.GROUP_EDIT.name())
                .anyRequest().authenticated();
    }
}
