package com.example.feedbackmessagingservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "event-management",url = "http://localhost:8081/api")
public interface EventProxy {

    @GetMapping("/events/{id}")
    void validateEventById(@PathVariable("id") int id);

}
