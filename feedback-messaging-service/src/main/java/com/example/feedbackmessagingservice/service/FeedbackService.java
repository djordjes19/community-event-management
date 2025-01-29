package com.example.feedbackmessagingservice.service;

import com.example.feedbackmessagingservice.feign.EventProxy;
import com.example.feedbackmessagingservice.feign.UserProxy;
import com.example.feedbackmessagingservice.model.Feedback;
import com.example.feedbackmessagingservice.model.Message;
import com.example.feedbackmessagingservice.repositories.FeedbackRepository;
import com.example.feedbackmessagingservice.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private EventProxy eventProxy;

    @Autowired
    private UserProxy userProxy;

    public void submitFeedback(Feedback feedback) {
        try {
            eventProxy.validateEventById(feedback.getEventId());
        } catch (Exception e) {
            throw new RuntimeException("Invalid event ID");
        }

        try {
            userProxy.getUserById(feedback.getUserId());
        } catch (Exception e) {
            throw new RuntimeException("Invalid user ID");
        }

        if (feedback.getRating() < 1 || feedback.getRating() > 5) {
            throw new RuntimeException("Rating must be between 1 and 5");
        }

        feedback.setSubmittedAt(LocalDateTime.now());
        feedbackRepository.save(feedback);
    }

    public List<Feedback> getFeedbackForEvent(int eventId) {
        return feedbackRepository.findByEventId(eventId);
    }

    public List<Feedback> getFeedbackByUser(int userId) {
        return feedbackRepository.findByUserId(userId);
    }

    public Message sendMessage(Message message) {
        if (message.getEventId() == 0 || message.getUserId() == 0 || message.getMessage() == null || message.getMessage().isEmpty()) {
            throw new IllegalArgumentException("Event ID, User ID, and Message content are required.");
        }

        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByUser(int userId) {
        return messageRepository.findByUserId(userId);
    }

    public List<Message>  getMessagesByEvent(int eventId) {
        return messageRepository.findByEventId(eventId);
    }

    public String getFeedbackRating(){
        List<Feedback> feedbacklist = feedbackRepository.findAll();
        int sum=0;
        for (Feedback feedback : feedbacklist) {
            sum+=feedback.getRating();
        }
        float rez = (float) sum /feedbacklist.size();
        return "Prosecna vrednost rejtinga svih odgovora je: " + String.format("%.2f", rez);
    }






}
