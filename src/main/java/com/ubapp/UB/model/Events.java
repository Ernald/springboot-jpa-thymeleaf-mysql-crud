package com.ubapp.UB.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Column(name = "event_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Specify the expected date format
    private Date eventDate;

    @OneToMany(mappedBy = "event")
    private List<Visitors> visitors;

    public Events(){}

    public Events(Long eventId, String eventName, String eventType, Date eventDate, List<Visitors> visitors) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventDate = eventDate;
        this.visitors = visitors;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public List<Visitors> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitors> visitors) {
        this.visitors = visitors;
    }
}