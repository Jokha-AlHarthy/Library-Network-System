package com.mini.project.controllers;

import com.mini.project.dto.PublisherDTO;
import com.mini.project.entities.Publisher;
import com.mini.project.services.PublisherService;
import jakarta.validation.Valid;
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
    public Long addPublisher(@Valid @RequestBody PublisherDTO dto) {
        return publisherService.addPublisher(
                dto.getPublisherName(),
                dto.getPublisherAddress(),
                dto.getPublisherContactEmail());
    }

    @GetMapping("getAll")
    public List<PublisherDTO> getAllPublishers() {
        List<PublisherDTO> publishers = PublisherDTO.convertToDTO(publisherService.getAllPublishers());
        return publishers;
    }

    @GetMapping("getById")
    public PublisherDTO getById(@RequestParam Long id) {
        return PublisherDTO.convertToDTO(publisherService.getById(id));
    }

    @PutMapping("update")
    public PublisherDTO updatePublisher(@Valid @RequestBody PublisherDTO dto) throws Exception {
        return PublisherDTO.convertToDTO(publisherService.updatePublisher(
                dto.getPublisherId(),
                dto.getPublisherName(),
                dto.getPublisherAddress(),
                dto.getPublisherContactEmail()));
    }

    @DeleteMapping("deleteById")
    public Boolean deletePublisher(@RequestParam Long id) {
        return publisherService.deleteById(id);
    }
}
