package com.example.eventmanagement.repositories;

import com.example.eventmanagement.model.EventParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventParticipantRepository extends JpaRepository<EventParticipant, Integer> {

    List<EventParticipant> findByEventId(int eventId);
    List<EventParticipant> findByEventIdAndUserId(int eventId, int userId);
}
