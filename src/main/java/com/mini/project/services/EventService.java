package com.mini.project.services;

import com.mini.project.entities.Event;
import com.mini.project.exceptions.ResourceNotFoundException;
import com.mini.project.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    EventRepository eventRepository;
    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    //Add service
    public Long addEvent(String title, Date eventDate, String description){
        Event event =  new Event();
        event.setIsActive(true);
        event.setCreatedDate(new Date());
        event.setTitle(title);
        event.setEventDate(eventDate);
        event.setDescription(description);
        event = eventRepository.save(event);
        return event.getId();
    }

    //Get All events service
    public List<Event> getAllEvents() {
        return eventRepository.getAllEvents();
    }

    //Get Event By Id service
    public Event getById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent() && event.get().getIsActive()) {
            return event.get();
        }
        throw new ResourceNotFoundException("Event not found with id: " + id);
    }

    //Update service
    public Event updateEvent(Long id, String updateTitle, Date updateEventDate, String updateDescription) throws Exception{
        Event eventToUpdate = eventRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found with id: " + id));

        if (!eventToUpdate.getIsActive()) {
            throw new ResourceNotFoundException("Event not found with id: " + id);
        }
        eventToUpdate.setUpdatedDate(new Date());
        eventToUpdate.setTitle(updateTitle);
        eventToUpdate.setEventDate(updateEventDate);
        eventToUpdate.setDescription(updateDescription);
        eventToUpdate = eventRepository.save(eventToUpdate);
        return eventToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id){
        Event deleteEvent = eventRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found with id: " + id));

        if (!deleteEvent.getIsActive()) {
            throw new ResourceNotFoundException("Event not found with id: " + id);
        }
        deleteEvent.setIsActive(false);
        deleteEvent.setUpdatedDate(new Date());
        eventRepository.save(deleteEvent);
        return true;
    }
}
