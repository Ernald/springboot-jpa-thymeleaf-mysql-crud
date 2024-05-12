package com.ubapp.UB.services;

import com.ubapp.UB.model.Leads;
import com.ubapp.UB.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadServiceImpl implements LeadService {

    @Autowired
    LeadRepository leadRepository;

    @Override
    public Leads findById(Long id) {
        return leadRepository.findById(id).orElse(null);

    }

    @Override
    public List<Leads> findAll() {
        return leadRepository.findAll();
    }

    @Override
    public Leads save(Leads lead) {
        return leadRepository.save(lead);
    }

    @Override
    public void deleteById(Long id) {
        leadRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdate(Leads lead) {
        leadRepository.save(lead);
    }
}
