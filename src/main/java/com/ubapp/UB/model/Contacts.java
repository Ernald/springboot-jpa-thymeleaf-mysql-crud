package com.ubapp.UB.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "contacts")
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long contactId;

    @OneToMany(mappedBy = "contact")
    private List<Visitors> visitors;

    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "contact_email")
    private String contactEmail;

    public Contacts(){}

    public Contacts(Long contactId, List<Visitors> visitors, String contactName, String contactPhone, String contactEmail) {
        this.contactId = contactId;
        this.visitors = visitors;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public List<Visitors> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitors> visitors) {
        this.visitors = visitors;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}