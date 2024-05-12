package com.ubapp.UB.controller;

import com.ubapp.UB.model.Contacts;
import com.ubapp.UB.model.Events;
import com.ubapp.UB.model.Leads;
import com.ubapp.UB.model.Visitors;
import com.ubapp.UB.repository.ContactRepository;
import com.ubapp.UB.repository.EventRepository;
import com.ubapp.UB.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
public class VisitorsController {

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    VisitorRepository visitorRepository;

    @GetMapping("/visitors")
    public String showContactsPage(Model model) {
        List<Contacts> contacts = contactRepository.findAll();
        List<Events> events = eventRepository.findAll();
        List<Visitors> visitors = visitorRepository.findAll();
        model.addAttribute("visitors", visitors);
        model.addAttribute("contacts",contacts);
        model.addAttribute("events",events);
        return "visitors";
    }

    @PostMapping("/visitors/add")
    public String addVisitor(@RequestParam("contactId") Long contactId,
                             @RequestParam("eventId") Long eventId,
                             @RequestParam("role") String role,
                             @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        // Retrieve the Contact and Event objects based on the provided IDs
        Contacts contact = contactRepository.findById(contactId).orElse(null);
        Events event = eventRepository.findById(eventId).orElse(null);

        // Check if either the contact or event is not found
        if (contact == null || event == null) {
            // Handle case when either contact or event is not found
            // You can redirect to an error page or display a message
            return "redirect:/error"; // Redirect to the error page
        }

        // Create a new Visitor entity
        Visitors visitor = new Visitors();
        visitor.setContact(contact);
        visitor.setEvent(event);
        visitor.setVisitorRole(role);
        visitor.setVisitorVisitDate(date);

        // Save the visitor to the database
        visitorRepository.save(visitor);

        // Redirect to a success page or the page where you want to navigate after adding the visitor
        return "redirect:/visitors";
    }

    @PostMapping("/visitors/delete")
    public String deleteVisitor(@RequestParam("visitorId") Long visitorId) {
        // Find the visitor by ID
        Visitors visitor = visitorRepository.findById(visitorId).orElse(null);
        if (visitor != null) {
            // Delete the visitor from the database
            visitorRepository.delete(visitor);
        }
        // Redirect back to the visitors page
        return "redirect:/visitors";
    }

    @GetMapping("/visitors/edit/{id}")
    public String editVisitor(@PathVariable Long id, Model model) {
        List<Contacts> contacts = contactRepository.findAll();
        List<Events> events = eventRepository.findAll();
        Visitors visitor = visitorRepository.findById(id).orElse(null);
        if (visitor == null) {
            // Handle case when visitor is not found
            return "redirect:/error";
        }
        model.addAttribute("events", events);
        model.addAttribute("contacts", contacts);
        model.addAttribute("visitor", visitor);
        return "editVisitor"; // Assuming the name of your edit page is "editVisitor.html"
    }

    @PostMapping("/visitors/update")
    public String updateVisitor(@ModelAttribute Visitors visitor,
                                @RequestParam("eventId") Long eventId,
                                @RequestParam("contactId") Long contactId,
                                @RequestParam("visitorVisitDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date visitorVisitDate,
                                RedirectAttributes redirectAttributes) {

        // Retrieve the selected event and contact from the database
        Events event = eventRepository.findById(eventId).orElse(null);
        Contacts contact = contactRepository.findById(contactId).orElse(null);

        // Validate if event exists
        if (event == null) {
            // Handle case when event is not found
            redirectAttributes.addFlashAttribute("errorMessage", "Error: Event not found.");
            return "redirect:/error";
        }

        // Set the event and contact for the visitor
        visitor.setEvent(event);
        visitor.setContact(contact);
        // Set the visitor visit date
        visitor.setVisitorVisitDate(visitorVisitDate);

        // Save the updated visitor to the database
        visitorRepository.save(visitor);
        return "redirect:/visitors"; // Redirect back to the visitors page
    }


}
