package com.mini.project.controllers;

import com.mini.project.entities.Event;
import com.mini.project.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("event")
public class EventController {
    EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("add")
    public Long addEvent(@RequestParam String title,
                         @RequestParam Date eventDate,
                         @RequestParam String description) {
        return eventService.addEvent(title, eventDate, description);
    }

    @GetMapping("getAll")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("getById")
    public Event getById(@RequestParam Long id) {
        return eventService.getById(id);
    }

    @PutMapping("update")
    public Event updateEvent(@RequestParam Long id,
                             @RequestParam String updateTitle,
                             @RequestParam Date updateEventDate,
                             @RequestParam String updateDescription) throws Exception {
        return eventService.updateEvent(id, updateTitle,
                updateEventDate, updateDescription);
    }

    @DeleteMapping("deleteById")
    public Boolean deleteEvent(@RequestParam Long id) {
        return eventService.deleteById(id);
    }
}
