package com.ubapp.UB.controller;

import com.ubapp.UB.model.Events;
import com.ubapp.UB.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EventsController {

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/events")
    public String showContactsPage(Model model) {
        List<Events> events = eventRepository.findAll();
        model.addAttribute("events",events);
        model.addAttribute("event", new Events());
        return "events";
    }

    @PostMapping("/event/add")
    public String addEvent(@ModelAttribute("event") Events event) {
        // Save the event to the database
        eventRepository.save(event);
        // Redirect to a confirmation page or another appropriate page
        return "redirect:/events";
    }

    @PostMapping("/events/delete/{eventId}")
    public String deleteEvent(@PathVariable Long eventId) {
        // Retrieve the event from the database
        Events event = eventRepository.findById(eventId).orElse(null);
        if (event == null) {
            // Handle case when event is not found
            // You can redirect to an error page or display a message
            return "redirect:/events"; // Redirect to the events page
        }
        // Delete the event from the database
        eventRepository.delete(event);
        // Redirect to the events page
        return "redirect:/events";
    }

    @GetMapping("/events/edit/{eventId}")
    public String showEditEventForm(@PathVariable Long eventId, Model model) {
        Events event = eventRepository.findById(eventId).orElse(null);
        if (event == null) {
            // Handle case when event is not found
            // You can redirect to an error page or display a message
            return "redirect:/events"; // Redirect to the events page
        }
        model.addAttribute("event", event);
        return "editEvent"; // Return the name of the edit event HTML page
    }

    @PostMapping("/events/update")
    public String updateEvent(@ModelAttribute("event") Events event) {
        // Retrieve the event from the database
        Events existingEvent = eventRepository.findById(event.getEventId()).orElse(null);
        if (existingEvent == null) {
            // Handle case when event is not found
            // You can redirect to an error page or display a message
            return "redirect:/events"; // Redirect to the events page
        }

        // Update the properties of the existing event with the new values
        existingEvent.setEventName(event.getEventName());
        existingEvent.setEventType(event.getEventType());
        existingEvent.setEventDate(event.getEventDate());

        // Save the updated event to the database
        eventRepository.save(existingEvent);

        // Redirect to the events page or any other desired page
        return "redirect:/events";
    }


}
