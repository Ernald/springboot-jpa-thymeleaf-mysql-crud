package com.ubapp.UB.repository;

import com.ubapp.UB.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contacts,Long> {
}
