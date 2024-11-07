package com.pnu.system.common.security;

import com.pnu.system.common.security.audit.TimetableAuditorAware;
import com.pnu.system.common.security.filter.TimetableSecurityFilter;
import com.pnu.system.common.security.provider.InternalUserProvider;
import com.pnu.system.common.security.provider.RestInternalUserProvider;
import com.pnu.system.common.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.pnu.system.common.constant.PermissionName.INTERNAL_USE;

@Slf4j
@EnableWebSecurity
public abstract class TimetableCommonWebSecurityConfig {

    @Autowired
    private TimetableSecurityFilter securityFilter;

    @Bean
    public AuditorAware<String> timetableAuditorAware() {
        return new TimetableAuditorAware();
    }

    @Bean
    @ConditionalOnMissingBean(InternalUserProvider.class)
    public InternalUserProvider restInternalUserProvider(JwtUtils jwtUtils) {
        return new RestInternalUserProvider(jwtUtils);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        log.info("Loading base web security configuration");
        http.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(this::configureAllHttpRequests);

        return http.build();
    }

    private void configureAllHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry.requestMatchers("/**/internal/**").hasAuthority(INTERNAL_USE.name());
        configureHttpRequests(registry);
    }

    protected void configureHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry.anyRequest().authenticated();
    }

}
