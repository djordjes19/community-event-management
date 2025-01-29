package com.example.feedbackmessagingservice.exceptions;


import java.time.LocalDateTime;

public class ErrorEntity {
    private String message;
    private LocalDateTime dateTime;

    public ErrorEntity(String message, LocalDateTime now) {
    }
}
