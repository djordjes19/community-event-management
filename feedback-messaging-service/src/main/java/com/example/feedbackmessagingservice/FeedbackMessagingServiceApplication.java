package com.example.feedbackmessagingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeedbackMessagingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedbackMessagingServiceApplication.class, args);
    }

}
