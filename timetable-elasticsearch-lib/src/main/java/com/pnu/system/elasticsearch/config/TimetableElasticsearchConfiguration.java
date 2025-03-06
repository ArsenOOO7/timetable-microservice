package com.pnu.system.elasticsearch.config;


import org.elasticsearch.client.sniff.Sniffer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.pnu.system",
        repositoryFactoryBeanClass = TimetableElasticsearchFactoryBean.class)
public class TimetableElasticsearchConfiguration extends ElasticsearchConfigurationSupport {

    /*
    TODO 6/14/24: Replace with good fix, maybe...
     U need to override with the help of Sniffer builder. If u do this, I'll buy any ice-cream for being such clever boy/girl :)
     */
    @Bean
    public Sniffer sniffer() {
        return null;
    }
}
