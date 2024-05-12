package com.ubapp.UB.repository;

import com.ubapp.UB.model.Visitors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitors,Long> {
}
