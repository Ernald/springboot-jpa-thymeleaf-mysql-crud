package com.ubapp.UB.services;

import com.ubapp.UB.model.Leads;

import java.util.List;

public interface LeadService {
    Leads findById(Long id);
    List<Leads> findAll();
    Leads save(Leads lead);
    void deleteById(Long id);
    void saveOrUpdate (Leads leads);
}
