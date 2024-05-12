package com.ubapp.UB.repository;

import com.ubapp.UB.model.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Events,Long> {
}
