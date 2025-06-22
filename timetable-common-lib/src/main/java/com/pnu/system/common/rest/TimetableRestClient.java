package com.pnu.system.common.rest;

import com.pnu.system.common.security.provider.InternalUserProvider;
import com.pnu.system.common.utils.TimetableRestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Component
public class TimetableRestClient {

    private final WebClient webClient;
    private final InternalUserProvider internalUserProvider;

    public TimetableRestClient(InternalUserProvider internalUserProvider) {
        this.internalUserProvider = internalUserProvider;
        this.webClient = WebClient.create();
    }

    public <T> T get(String url, Class<T> responseType) {
        return getMono(url, responseType).block();
    }

    public <T> T post(String url, Object body, Class<T> responseType) {
        return postMono(url, body, responseType).block();
    }

    public <T> List<T> getList(String url, Class<? extends T[]> responseType) {
        return getMono(url, responseType).map(this::asList).block();
    }

    public <T> List<T> getList(String url, Map<String, Object> queryParams, Class<? extends T[]> responseType) {
        UriBuilder builder = new DefaultUriBuilderFactory(url).builder();
        TimetableRestUtils.addQueryParameters(builder, queryParams);
        return getMono(builder.build().toString(), responseType).map(this::asList).block();
    }

    public <T> List<T> postForList(String url, Object body, Class<? extends T[]> responseType) {
        return postMono(url, body, responseType).map(this::asList).block();
    }

    public <T> Mono<T> getMono(String url, Class<T> responseType) {
        return webClient.get()
                .uri(url)
                .headers(this::addHeaders)
                .retrieve()
                .bodyToMono(responseType);
    }

    public <T> Mono<T> postMono(String url, Object body, Class<T> responseType) {
        return webClient.post()
                .uri(url)
                .bodyValue(body)
                .headers(this::addHeaders)
                .retrieve()
                .bodyToMono(responseType);
    }

    private <T> List<T> asList(T[] elements) {
        return ArrayUtils.isEmpty(elements) ? Collections.emptyList() : Arrays.asList(elements);
    }

    private void addHeaders(HttpHeaders httpHeaders) {
        httpHeaders.set(HttpHeaders.AUTHORIZATION, internalUserProvider.getInternalTokenForRequest());
    }
}
