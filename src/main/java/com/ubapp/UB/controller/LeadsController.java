package com.ubapp.UB.controller;

import com.ubapp.UB.model.Contacts;
import com.ubapp.UB.model.Leads;
import com.ubapp.UB.repository.ContactRepository;
import com.ubapp.UB.repository.LeadRepository;
import com.ubapp.UB.services.LeadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class LeadsController {

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    LeadRepository leadRepository;
    @Autowired
    LeadServiceImpl leadService;

    @GetMapping("/leads")
    public String getAllLeads(Model model) {
        List<Contacts> contacts = contactRepository.findAll();
        List<Leads> leads = leadRepository.findAllWithContact();
        model.addAttribute("contacts", contacts);
        model.addAttribute("leads", leads);
        return "leads"; // Assuming "leads" is the name of your Thymeleaf template
    }

    @PostMapping("/leads/add")
    public String addLead(@RequestParam(value = "leadContacted", defaultValue = "false") boolean leadContacted,
                          @RequestParam("leadInterest") String leadInterest,
                          @RequestParam("leadExperience") String leadExperience,
                          @RequestParam("leadRole") String leadRole,
                          @RequestParam("leadSource") String leadSource,
                          @RequestParam("contactId") Long contactId) {
        // Retrieve the Contact object based on the contactId
        Contacts contact = contactRepository.findById(contactId).orElse(null);
        if (contact == null) {
            // Handle case when contact is not found
            // You can redirect to an error page or display a message
            return "redirect:/leads"; // Redirect to the leads page
        }
        // Create a new lead entity
        Leads lead = new Leads();
        lead.setContact(contact);
        lead.setLeadInterest(leadInterest);
        lead.setLeadExperience(leadExperience);
        lead.setLeadRole(leadRole);
        lead.setLeadSource(leadSource);
        lead.setLeadContacted(leadContacted);
        lead.setLeadContactDate(new Date());
        lead.setLeadRegDate(new Date());
        // Save the lead to the database
        leadRepository.save(lead);
        // Redirect to the leads page
        return "redirect:/leads";
    }

    @GetMapping("/leads/edit/{leadId}")
    public String showEditLeadForm(@PathVariable("leadId") Long leadId, Model model) {
        // Retrieve lead information from your data source (e.g., database) based on leadId
        Leads lead = leadService.findById(leadId); // Assuming you have a lead service to handle data operations
        List<Contacts> contacts = contactRepository.findAll();

        // Add lead object to the model
        model.addAttribute("lead", lead);
        model.addAttribute("contacts",contacts);

        // Return the view name (editLead.html in this case)
        return "editLead";
    }


    @PostMapping("/leads/update")
    public String updateLead(@RequestParam(value = "leadContacted", defaultValue = "false") boolean leadContacted,
                             @RequestParam("leadInterest") String leadInterest,
                             @RequestParam("leadExperience") String leadExperience,
                             @RequestParam("leadRole") String leadRole,
                             @RequestParam("leadSource") String leadSource,
                             @RequestParam("contactId") Long contactId,
                             @RequestParam("leadId") Long leadId) {
        // Retrieve the Contact object based on the contactId
        Contacts contact = contactRepository.findById(contactId).orElse(null);
        if (contact == null) {
            // Handle case when contact is not found
            // You can redirect to an error page or display a message
            return "redirect:/leads"; // Redirect to the leads page
        }
        // Fetch the lead object from the database based on its ID
        // Assuming you have a method to find the lead by ID in your repository
        Leads lead = leadRepository.findById(leadId).orElse(null);
        if (lead == null) {
            // Handle case when lead is not found
            // You can redirect to an error page or display a message
            return "redirect:/leads"; // Redirect to the leads page
        }
        // Update the lead entity
        lead.setContact(contact);
        lead.setLeadInterest(leadInterest);
        lead.setLeadExperience(leadExperience);
        lead.setLeadRole(leadRole);
        lead.setLeadSource(leadSource);
        lead.setLeadContacted(leadContacted);
        lead.setLeadContactDate(new Date());
        // Save the updated lead to the database
        leadRepository.save(lead);
        // Redirect to the leads page
        return "redirect:/leads";
    }

    //Delete Leads
    @PostMapping("/leads/delete")
    public String deleteLead(@RequestParam("leadId") Long leadId) {
        // Retrieve the lead from the database based on the leadId
        Optional<Leads> leadOptional = leadRepository.findById(leadId);

        if (leadOptional.isPresent()) {
            // Lead exists, delete it
            leadRepository.delete(leadOptional.get());
        } else {
            // Lead does not exist, handle error or return appropriate response
            // You can redirect to an error page or display a message
            return "redirect:/leads"; // Redirect to the leads page
        }

        // Redirect to the leads page after successful deletion
        return "redirect:/leads";
    }


}
