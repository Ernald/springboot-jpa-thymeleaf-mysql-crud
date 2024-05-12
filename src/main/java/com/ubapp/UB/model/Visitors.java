package com.ubapp.UB.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "visitors")
public class Visitors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visitor_id")
    private Long visitorId;

    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contacts contact;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Events event;

    @Column(name = "visitor_role")
    private String visitorRole;

    @Column(name = "visitor_visit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Specify the expected date format
    private Date visitorVisitDate;

    @Column(name = "visitor_attendance")
    private boolean visitorAttendance;

    public Visitors(){}

    public Visitors(Long visitorId, Contacts contact, Events event, String visitorRole, Date visitorVisitDate, boolean visitorAttendance) {
        this.visitorId = visitorId;
        this.contact = contact;
        this.event = event;
        this.visitorRole = visitorRole;
        this.visitorVisitDate = visitorVisitDate;
        this.visitorAttendance = visitorAttendance;
    }

    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    public Contacts getContact() {
        return contact;
    }

    public void setContact(Contacts contact) {
        this.contact = contact;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }

    public String getVisitorRole() {
        return visitorRole;
    }

    public void setVisitorRole(String visitorRole) {
        this.visitorRole = visitorRole;
    }

    public Date getVisitorVisitDate() {
        return visitorVisitDate;
    }

    public void setVisitorVisitDate(Date visitorVisitDate) {
        this.visitorVisitDate = visitorVisitDate;
    }

    public boolean isVisitorAttendance() {
        return visitorAttendance;
    }

    public void setVisitorAttendance(boolean visitorAttendance) {
        this.visitorAttendance = visitorAttendance;
    }
}
