package com.arsen.gateaway.filter;

import com.arsen.gateaway.dto.ErrorDto;
import com.arsen.gateaway.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final WebClient.Builder wwebClintBuilder;
    private final ObjectMapper objectMapper;

    @Value("${jwt.token.header}")
    private String header;

    public AuthenticationFilter(WebClient.Builder wwebClintBuilder) {
        super(Config.class);
        this.wwebClintBuilder = wwebClintBuilder;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            ServerHttpRequest serverHttpRequest = exchange.getRequest();
            String token = serverHttpRequest.getHeaders().getFirst(header);

            if(token == null){
                return chain.filter(exchange);
            }

            return wwebClintBuilder.build().post()
                    .uri("lb://identity-access/api/auth")
                    .header(AUTHORIZATION, token)
                    .retrieve()
                    .bodyToMono(UserDto.class)
                    .map(response -> {

                        log.info("User {} with role {} to {}", response.getEmail(), response.getRole(), serverHttpRequest.getPath());
                        exchange.getRequest().mutate().header("email", response.getEmail());
                        exchange.getRequest().mutate().header("role", response.getRole());
                        exchange.getRequest().mutate().header(AUTHORIZATION, token);

                        return exchange;
                    })
                    .flatMap(chain::filter)
                    .onErrorResume(error -> {

                        String errorMessage = HttpStatus.BAD_GATEWAY.getReasonPhrase();
                        HttpStatusCode httpStatus = HttpStatus.BAD_GATEWAY;

                        if(error instanceof WebClientResponseException webClientResponseException){
                            errorMessage = webClientResponseException.getStatusText();
                            httpStatus = webClientResponseException.getStatusCode();
                        }

                        log.error("[{}] ({}): {}", serverHttpRequest.getPath(), httpStatus, errorMessage);

                        return onError(exchange, httpStatus, errorMessage);
                    });
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatusCode httpStatus, String errorMessage) {

        var response = exchange.getResponse();
        DataBufferFactory dataBufferFactory = response.bufferFactory();
        response.setStatusCode(httpStatus);

        try{
            response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            byte[] bytes = objectMapper.writeValueAsBytes(new ErrorDto(errorMessage));
            return response.writeWith(Mono.just(bytes).map(dataBufferFactory::wrap));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return response.setComplete();
    }

    @NoArgsConstructor
    public static class Config{

    }

}
