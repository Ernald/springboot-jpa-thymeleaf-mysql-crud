package com.ubapp.UB.controller;

import com.ubapp.UB.model.Contacts;
import com.ubapp.UB.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/contacts")
    public String showContacts(Model model) {
        // Add logic to fetch contacts from the database and pass them to the view
        List<Contacts> contacts = contactRepository.findAll();
        model.addAttribute("contacts", contacts);
        model.addAttribute("contact", new Contacts());
        return "contacts";
    }

    @PostMapping("/contacts/add")
    public String addContact(Contacts contact) {
        // Save the contact to the database
        contactRepository.save(contact);
        return "redirect:/contacts";
    }

    @GetMapping("/contacts/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        Optional<Contacts> contactOptional = contactRepository.findById(id);
        if (contactOptional.isPresent()) {
            model.addAttribute("contact", contactOptional.get());
            return "editContacts";
        } else {
            // Handle contact not found
            return "redirect:/contacts";
        }
    }

    // Mapping to handle form submission for updating contacts
    @PostMapping("/contacts/update")
    public String updateContact(@ModelAttribute("contact") Contacts updatedContact) {
        contactRepository.save(updatedContact); // Save the updated contact to the database
        return "redirect:/contacts"; // Redirect to the contacts listing page
    }

    @PostMapping("/contacts/delete/{id}")
    public String deleteContact(@PathVariable("id") Long id) {
        contactRepository.deleteById(id);
        return "redirect:/contacts";
    }

}
