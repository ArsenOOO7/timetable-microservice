package com.arsen.gateway.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableDiscoveryClient
public class GatewayConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder loadWebClientBuilder(){
        return WebClient.builder();
    }
}
