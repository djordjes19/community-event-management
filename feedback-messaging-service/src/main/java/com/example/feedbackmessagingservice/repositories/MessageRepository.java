package com.example.feedbackmessagingservice.repositories;

import com.example.feedbackmessagingservice.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

  List<Message> findByUserId(Integer userId);
  List<Message> findByEventId(Integer eventId);

  }