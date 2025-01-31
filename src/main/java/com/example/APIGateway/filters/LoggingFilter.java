package com.example.APIGateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ServerWebExchange;

@Component
@Order(1)
public class LoggingFilter implements GlobalFilter {
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Incoming request: " + exchange.getRequest().getPath());
        logger.info("Checking if its gng inside ");

        return chain.filter(exchange).then(Mono.fromRunnable(() ->
                logger.info("Outgoing response: " + exchange.getResponse().getStatusCode())
        ));
    }
}