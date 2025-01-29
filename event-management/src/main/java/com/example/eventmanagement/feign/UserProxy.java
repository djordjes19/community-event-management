package com.example.eventmanagement.feign;

import com.example.eventmanagement.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-management",url = "http://localhost:8080/api")
public interface UserProxy {

    @GetMapping("/users/{id}")
    UserDTO getUserById(@PathVariable("id") Integer id);






}
