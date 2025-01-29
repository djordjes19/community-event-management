package com.example.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class RouterConfig {

    // Prepisati zahtev GET /home na endpoint .
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-management", r -> r.path("/api/users/**").uri("http://localhost:8080"))
                .route("event-management",r -> r.path("/api/events/**").uri("http://localhost:8081"))
                .route("feedback-messaging-service",r -> r.path("/api/feedback/**").uri("http://localhost:8082"))
                .route("feedback-messaging-service",r -> r.path("/api/messages/**").uri("http://localhost:8082"))
                .route("home", r -> r.path("/home").filters(f->f.rewritePath("/home", "/api/users")).uri("http://localhost:8080"))
                .build();
    }
}