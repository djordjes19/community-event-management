package com.example.eventmanagement.controller;

import com.example.eventmanagement.dto.UserDTO;
import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.model.EventParticipant;
import com.example.eventmanagement.service.EventService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping("/events")
    public Event createEvent(@RequestBody @Valid Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping("/events/{id}")
    public Event getEvent(@PathVariable @Min(1) Integer id) {
        return  eventService.getEventById(id);
    }

    @PutMapping("events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable("id") @Min(1) int id, @RequestBody Event updatedEvent) {
        Event event = eventService.updateEvent(id, updatedEvent);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") @Min(1) int id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/events/{id}/participants")
    public ResponseEntity<EventParticipant> addParticipant(@PathVariable("id") @Min(1) int eventId, @RequestBody int userId) {
        EventParticipant eventParticipant = eventService.addParticipantToEvent(eventId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventParticipant);
    }

    @GetMapping("/events/{id}/participants")
    public ResponseEntity<List<UserDTO>> getParticipantsForEvent(@PathVariable("id") @Min(1) int eventId) {
        List<UserDTO> participants = eventService.getParticipantsForEvent(eventId);
        return ResponseEntity.ok(participants);
    }

    @DeleteMapping("/events/{eventId}/participants/{userId}")
    public ResponseEntity<String> removeParticipantFromEvent(@PathVariable("eventId") @Min(1) int eventId, @PathVariable("userId") @Min(1) int userId) {
        try {
            eventService.removeParticipantFromEvent(eventId, userId);

            return ResponseEntity.ok("Participant removed successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }






}
