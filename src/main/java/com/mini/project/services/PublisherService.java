package com.mini.project.services;

import com.mini.project.entities.Author;
import com.mini.project.entities.Publisher;
import com.mini.project.exceptions.ResourceNotFoundException;
import com.mini.project.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    PublisherRepository publisherRepository;
    @Autowired
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    //Add service
    public Long addPublisher(String name, String address, String contactEmail){
        Publisher publisher =  new Publisher();
        publisher.setIsActive(true);
        publisher.setCreatedDate(new Date());
        publisher.setName(name);
        publisher.setAddress(address);
        publisher.setContactEmail(contactEmail);
        publisher = publisherRepository.save(publisher);
        return publisher.getId();
    }

    //Get All publishers service
    public List<Publisher> getAllPublishers() {
        return publisherRepository.getAllPublishers();
    }

    //Get Publisher By Id service
    public Publisher getById(Long id) {
        Optional<Publisher> publisher = publisherRepository.findById(id);
        if (publisher.isPresent() && publisher.get().getIsActive()) {
            return publisher.get();
        }
        throw new ResourceNotFoundException("Publisher not found with id: " + id);
    }

    //Update service
    public Publisher updatePublisher(Long id, String updateName, String updateAddress, String updateContactEmail) throws Exception{
        Publisher publisherToUpdate = publisherRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Publisher not found with id: " + id));

        if (!publisherToUpdate.getIsActive()) {
            throw new ResourceNotFoundException("Publisher not found with id: " + id);
        }
        publisherToUpdate.setUpdatedDate(new Date());
        publisherToUpdate.setName(updateName);
        publisherToUpdate.setAddress(updateAddress);
        publisherToUpdate.setContactEmail(updateContactEmail);
        publisherToUpdate = publisherRepository.save(publisherToUpdate);
        return publisherToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id){
        Publisher deletePublisher = publisherRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Publisher not found with id: " + id));

        if (!deletePublisher.getIsActive()) {
            throw new ResourceNotFoundException("Publisher not found with id: " + id);
        }
        deletePublisher.setIsActive(false);
        deletePublisher.setUpdatedDate(new Date());
        publisherRepository.save(deletePublisher);
        return true;
    }
}
