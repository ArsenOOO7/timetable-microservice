package com.pnu.system.common.security;

import com.pnu.system.common.security.audit.TimetableAuditorAware;
import com.pnu.system.common.security.provider.InternalUserProvider;
import com.pnu.system.common.security.provider.RestInternalUserProvider;
import com.pnu.system.common.utils.JwtUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class TimetableWebConfig {

    //TODO: Ice-cream for fixing default credential problem ;)

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

    @Bean
    @ConditionalOnMissingBean(InternalUserProvider.class)
    public InternalUserProvider restInternalUserProvider(JwtUtils jwtUtils) {
        return new RestInternalUserProvider(jwtUtils);
    }

    @Bean
    public AuditorAware<String> timetableAuditorAware() {
        return new TimetableAuditorAware();
    }
}
