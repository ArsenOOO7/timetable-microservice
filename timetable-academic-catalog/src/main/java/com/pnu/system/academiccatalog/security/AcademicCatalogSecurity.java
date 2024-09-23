package com.pnu.system.academiccatalog.security;

import com.pnu.system.common.security.TimetableCommonWebSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

import static com.pnu.system.common.constant.PermissionName.CHAIR_EDIT;
import static com.pnu.system.common.constant.PermissionName.DEPARTMENT_EDIT;
import static com.pnu.system.common.constant.PermissionName.EDUCATIONAL_PROGRAM_EDIT;
import static com.pnu.system.common.constant.PermissionName.KNOWLEDGE_EDIT;
import static com.pnu.system.common.constant.PermissionName.SPECIALTY_EDIT;
import static com.pnu.system.common.constant.PermissionName.SUBJECT_EDIT;

@Slf4j
@Configuration
public class AcademicCatalogSecurity extends TimetableCommonWebSecurityConfig {
    @Override
    protected void configureHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry
                .requestMatchers("/chair/**").hasAuthority(CHAIR_EDIT.name())
                .requestMatchers("/department/**").hasAuthority(DEPARTMENT_EDIT.name())
                .requestMatchers("/educationProgram/**").hasAuthority(EDUCATIONAL_PROGRAM_EDIT.name())
                .requestMatchers("/knowledgeDomain/**").hasAuthority(KNOWLEDGE_EDIT.name())
                .requestMatchers("/specialty/**").hasAuthority(SPECIALTY_EDIT.name())
                .requestMatchers("/subject/**").hasAuthority(SUBJECT_EDIT.name())
                .anyRequest().authenticated();
    }
}
