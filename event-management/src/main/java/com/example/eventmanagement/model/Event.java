package com.example.eventmanagement.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "Events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 255)
    private String location;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    //    @ManyToOne
    @Column(name = "organizer_id", nullable = false)
    private int organizerId;  // This should match the column name in the database


    public int getId() {
        return id;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

//    public User getOrganizer() {
//        return organizer;
//    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

//    public void setOrganizer(User organizer) {
//        this.organizer = organizer;
//    }
}


