package com.arsen.group.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.arsen.group.repository")
@ComponentScan(basePackages = "com.arsen.group.domain")
public class ElasticSearchConfiguration{

    @Value("${elasticsearch.address}")
    private String elasticAddress;
    @Value("${elasticsearch.port}")
    private int elasticPort;

    @Bean
    public RestClient getRestClient(){
        return RestClient.builder(new HttpHost(elasticAddress, elasticPort)).build();
    }

    @Bean
    public ElasticsearchTransport getElasticsearchTransport(){
        return new RestClientTransport(getRestClient(), new JacksonJsonpMapper());
    }

    @Bean
    public ElasticsearchClient getElasticsearchClient(){
        return new ElasticsearchClient(getElasticsearchTransport());
    }

}
