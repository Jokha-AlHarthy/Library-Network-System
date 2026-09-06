package com.mini.project.controllers;

import com.mini.project.entities.Publisher;
import com.mini.project.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("publisher")
public class PublisherController {
    PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping("add")
    public Long addPublisher(@RequestParam String name,
                             @RequestParam String address,
                             @RequestParam String contactEmail) {
        return publisherService.addPublisher(name, address, contactEmail);
    }

    @GetMapping("getAll")
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("getById")
    public Publisher getById(@RequestParam Long id) {
        return publisherService.getById(id);
    }

    @PutMapping("update")
    public Publisher updatePublisher(@RequestParam Long id,
                                     @RequestParam String updateName,
                                     @RequestParam String updateAddress,
                                     @RequestParam String updateContactEmail) throws Exception {
        return publisherService.updatePublisher(id, updateName,
                updateAddress, updateContactEmail);
    }

    @DeleteMapping("deleteById")
    public Boolean deletePublisher(@RequestParam Long id) {
        return publisherService.deleteById(id);
    }
}
