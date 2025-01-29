package com.example.feedbackmessagingservice.controller;

import com.example.feedbackmessagingservice.model.Feedback;
import com.example.feedbackmessagingservice.model.Message;
import com.example.feedbackmessagingservice.service.FeedbackService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedbackController {

    @Autowired
    private  FeedbackService feedbackService;

    @PostMapping("/feedback")
    public ResponseEntity<String> submitFeedback(@RequestBody @Valid Feedback feedback) {
        try {
            feedbackService.submitFeedback(feedback);
            return ResponseEntity.status(HttpStatus.CREATED).body("Feedback submitted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/feedback/{eventId}")
    public ResponseEntity<List<Feedback>> getFeedbackForEvent(@PathVariable @Min(1) int eventId) {
        List<Feedback> feedbackList = feedbackService.getFeedbackForEvent(eventId);
        if (feedbackList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(feedbackList);
    }

    @GetMapping("/feedback/users/{userId}")
    public ResponseEntity<List<Feedback>> getFeedbackByUser(@PathVariable("userId") @Min(1) int userId) {
        List<Feedback> feedbackList = feedbackService.getFeedbackByUser(userId);
        if (feedbackList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(feedbackList);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> sendMessage(@RequestBody @Valid Message message) {
        Message sentMessage = feedbackService.sendMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(sentMessage);
    }

    @GetMapping("/messages/users/{userId}")
    public ResponseEntity<List<Message>> getMessagesByUser(@PathVariable @Min(1) int userId) {
        List<Message> messages = feedbackService.getMessagesByUser(userId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/messages/events/{eventId}")
    public ResponseEntity<List<Message>> getMessagesByEvent(@PathVariable @Min(1) int eventId) {
        List<Message> messages = feedbackService.getMessagesByEvent(eventId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/feedback/rating")
    public String getFeedbackRating() {
        return feedbackService.getFeedbackRating();
    }

}
