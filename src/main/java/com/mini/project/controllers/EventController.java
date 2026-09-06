package com.mini.project.controllers;

import com.mini.project.dto.EventDTO;
import com.mini.project.entities.Event;
import com.mini.project.services.EventService;
import jakarta.validation.Valid;
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
    public Long addEvent(@Valid @RequestBody EventDTO dto) {
        return eventService.addEvent(
                dto.getEventTitle(),
                dto.getEventDate(),
                dto.getEventDescription());
    }

    @GetMapping("getAll")
    public List<EventDTO> getAllEvents() {
        List<EventDTO> events =
                EventDTO.convertToDTO(eventService.getAllEvents());
        return events;
    }

    @GetMapping("getById")
    public EventDTO getById(@RequestParam Long id) {
        return EventDTO.convertToDTO(eventService.getById(id));
    }

    @PutMapping("update")
    public EventDTO updateEvent(@Valid @RequestBody EventDTO dto) throws Exception {
        return EventDTO.convertToDTO(eventService.updateEvent(
                dto.getEventId(),
                dto.getEventTitle(),
                dto.getEventDate(),
                dto.getEventDescription()));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteEvent(@RequestParam Long id) {
        return eventService.deleteById(id);
    }
}
