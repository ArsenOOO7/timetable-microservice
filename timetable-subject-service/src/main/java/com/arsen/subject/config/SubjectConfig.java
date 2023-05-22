package com.arsen.subject.config;

import com.arsen.common.annotation.EnableCommon;
import com.arsen.subject.filter.JwtVerifyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableDiscoveryClient
@EnableCommon
public class SubjectConfig {

    @Autowired
    private JwtVerifyFilter jwtVerifyFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(auth -> auth.requestMatchers("/swagger/**").permitAll()
                        .anyRequest().hasAnyRole("ADMIN", "UNIT_MANAGER"))
                .formLogin().disable()
                .addFilterBefore(jwtVerifyFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic()
                .and().build();
    }
}
