package com.example.eventmanagement.service;

import com.example.eventmanagement.dto.UserDTO;
import com.example.eventmanagement.exceptions.EntityDoesNotExistException;
import com.example.eventmanagement.feign.UserProxy;
import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.model.EventParticipant;
import com.example.eventmanagement.repositories.EventParticipantRepository;
import com.example.eventmanagement.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private UserProxy userProxy;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventParticipantRepository eventParticipantRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }


    public Event getEventById(Integer id) {
        return  eventRepository.findById(id).orElseThrow(()
                -> new EntityDoesNotExistException("Event with id: " + id + " not found."));
    }

    public Event updateEvent(int id, Event updatedEvent) {
        Event existingEvent = getEventById(id);

        if (updatedEvent.getTitle() != null && !updatedEvent.getTitle().isEmpty()) {
            existingEvent.setTitle(updatedEvent.getTitle());
        }
        if (updatedEvent.getDescription() != null && !updatedEvent.getDescription().isEmpty()) {
            existingEvent.setDescription(updatedEvent.getDescription());
        }
        if (updatedEvent.getLocation() != null && !updatedEvent.getLocation().isEmpty()) {
            existingEvent.setLocation(updatedEvent.getLocation());
        }
        if (updatedEvent.getDateTime() != null) {
            existingEvent.setDateTime(updatedEvent.getDateTime());
        }
        if (updatedEvent.getOrganizerId() > 0) {
            existingEvent.setOrganizerId(updatedEvent.getOrganizerId());
        }

        return eventRepository.save(existingEvent);
    }

    public void deleteEvent(Integer id) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(()
                        -> new EntityDoesNotExistException("Event with id: " + id + " not found."));
        eventRepository.delete(existingEvent);
    }

    public EventParticipant addParticipantToEvent(int eventId, int userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(()
                        -> new EntityDoesNotExistException("Event with id: " + eventId + " not found."));

        try {
            userProxy.getUserById(userId);
        } catch (Exception e) {
            throw new RuntimeException("User not found");
        }

        EventParticipant eventParticipant = new EventParticipant();
        eventParticipant.setEvent(event);
        eventParticipant.setUserId(userId);
        eventParticipant.setStatus(EventParticipant.Status.Pending);

        return eventParticipantRepository.save(eventParticipant);
    }

    public List<UserDTO> getParticipantsForEvent(int eventId) {
        List<EventParticipant> participants = eventParticipantRepository.findByEventId(eventId);

        return participants.stream()
                .map(participant -> {
                    try {
                        return userProxy.getUserById(participant.getUserId());
                    } catch (Exception e) {
                        throw new RuntimeException("User not found for participant with ID " + participant.getUserId());
                    }
                })
                .collect(Collectors.toList());
    }

    public void removeParticipantFromEvent(int eventId, int userId) {
        List<EventParticipant> eventParticipants = eventParticipantRepository.findByEventIdAndUserId(eventId, userId);

        if (eventParticipants.isEmpty()) {
            throw new RuntimeException("Participant not found for this event");
        }

        eventParticipantRepository.deleteAll(eventParticipants);
    }

}
