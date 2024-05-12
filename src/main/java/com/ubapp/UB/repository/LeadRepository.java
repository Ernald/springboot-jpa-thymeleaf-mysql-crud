package com.ubapp.UB.repository;

import com.ubapp.UB.model.Leads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeadRepository extends JpaRepository<Leads,Long> {
    @Query("SELECT l FROM Leads l JOIN FETCH l.contact")
    List<Leads> findAllWithContact();
}
