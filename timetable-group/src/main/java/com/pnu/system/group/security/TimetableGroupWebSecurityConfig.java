package com.pnu.system.group.security;

import com.pnu.system.common.constant.PermissionName;
import com.pnu.system.common.security.TimetableCommonWebSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

import static com.pnu.system.common.constant.PermissionName.INTERNAL_USE;

@Configuration
public class TimetableGroupWebSecurityConfig extends TimetableCommonWebSecurityConfig {

    @Override
    protected void configureHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry
                .requestMatchers("/**/internal/**").hasAuthority(INTERNAL_USE.name())
                .requestMatchers("/group/category/**").hasAuthority(PermissionName.GROUP_CATEGORY_EDIT.name())
                .requestMatchers("/group/**").hasAuthority(PermissionName.GROUP_EDIT.name())
                .anyRequest().authenticated();
    }
}
