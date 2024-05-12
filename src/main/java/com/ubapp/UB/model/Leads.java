package com.ubapp.UB.model;


import jakarta.persistence.*;

import java.util.Date;

    @Entity
    @Table(name = "leads")
    public class Leads {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "lead_id")
        private Long leadId;

        @OneToOne
        @JoinColumn(name = "contact_id", unique = true, nullable = false)
        private Contacts contact;

        @Column(name = "lead_interest")
        private String leadInterest;

        @Column(name = "lead_experience")
        private String leadExperience;

        @Column(name = "lead_role")
        private String leadRole;

        @Column(name = "lead_source")
        private String leadSource;

//        @ManyToOne
//        @JoinColumn(name = "lead_referee")
//        private LeadReferee leadReferee;

        @Column(name = "lead_reg_date", nullable = false)
        private Date leadRegDate;

        @Column(name = "lead_contact_date")
        private Date leadContactDate;

        @Column(name = "lead_contacted")
        private boolean leadContacted;

        public Leads(){}
        public Leads(Long leadId, Contacts contact, String leadInterest, String leadExperience, String leadRole, String leadSource, Date leadRegDate, Date leadContactDate, boolean leadContacted) {
            this.leadId = leadId;
            this.contact = contact;
            this.leadInterest = leadInterest;
            this.leadExperience = leadExperience;
            this.leadRole = leadRole;
            this.leadSource = leadSource;
            this.leadRegDate = leadRegDate;
            this.leadContactDate = leadContactDate;
            this.leadContacted = leadContacted;
        }

        public Long getLeadId() {
            return leadId;
        }

        public void setLeadId(Long leadId) {
            this.leadId = leadId;
        }

        public Contacts getContact() {
            return contact;
        }

        public void setContact(Contacts contact) {
            this.contact = contact;
        }

        public String getLeadInterest() {
            return leadInterest;
        }

        public void setLeadInterest(String leadInterest) {
            this.leadInterest = leadInterest;
        }

        public String getLeadExperience() {
            return leadExperience;
        }

        public void setLeadExperience(String leadExperience) {
            this.leadExperience = leadExperience;
        }

        public String getLeadRole() {
            return leadRole;
        }

        public void setLeadRole(String leadRole) {
            this.leadRole = leadRole;
        }

        public String getLeadSource() {
            return leadSource;
        }

        public void setLeadSource(String leadSource) {
            this.leadSource = leadSource;
        }

        public Date getLeadRegDate() {
            return leadRegDate;
        }

        public void setLeadRegDate(Date leadRegDate) {
            this.leadRegDate = leadRegDate;
        }

        public Date getLeadContactDate() {
            return leadContactDate;
        }

        public void setLeadContactDate(Date leadContactDate) {
            this.leadContactDate = leadContactDate;
        }

        public boolean isLeadContacted() {
            return leadContacted;
        }

        public void setLeadContacted(boolean leadContacted) {
            this.leadContacted = leadContacted;
        }
    }
