package com.example.eventmanagement.model;


import jakarta.persistence.*;


@Entity
@Table(name = "Event_Participants")
public class EventParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.Pending;

    public enum Status {
        Pending,
        Confirmed,
        Cancelled
    }

    public int getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }


    public Status getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}


