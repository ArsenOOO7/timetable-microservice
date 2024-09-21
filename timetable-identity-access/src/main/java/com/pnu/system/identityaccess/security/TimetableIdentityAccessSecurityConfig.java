package com.pnu.system.identityaccess.security;

import com.pnu.system.common.security.TimetableCommonWebSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

import static com.pnu.system.common.constant.PermissionName.ACADEMIC_STATUS_EDIT;
import static com.pnu.system.common.constant.PermissionName.ROLE_EDIT;
import static com.pnu.system.common.constant.PermissionName.TEACHER_PROFILE_EDIT;
import static com.pnu.system.common.constant.PermissionName.TEACHER_PROFILE_RESTRICTED_EDIT;
import static com.pnu.system.common.constant.PermissionName.USER_EDIT;

@Slf4j
@Configuration
public class TimetableIdentityAccessSecurityConfig extends TimetableCommonWebSecurityConfig {

    @Override
    protected void configreHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .requestMatchers("/role/**", "/permission/**").hasAuthority(ROLE_EDIT.name())
                .requestMatchers("/academicStatus").hasAuthority(ACADEMIC_STATUS_EDIT.name())
                .requestMatchers("/teacher/profile").hasAnyAuthority(TEACHER_PROFILE_EDIT.name(), TEACHER_PROFILE_RESTRICTED_EDIT.name())
                .requestMatchers("/user/**").hasAnyAuthority(USER_EDIT.name())
                .anyRequest().authenticated();
    }
}