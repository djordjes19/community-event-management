package com.example.feedbackmessagingservice.repositories;

import com.example.feedbackmessagingservice.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    List<Feedback> findByEventId(int eventId);

    List<Feedback> findByUserId(int userId);
}