package com.mini.project.controllers;

import com.mini.project.dto.AuthorDTO;
import com.mini.project.entities.Author;
import com.mini.project.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {
    AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("add")
    public Long addAuthor(@Valid @RequestBody AuthorDTO dto) {
        return authorService.addAuthor( dto.getAuthorName(),
                dto.getAuthorNationality(),
                dto.getAuthorBiography());
    }

    @GetMapping("getAll")
    public List<AuthorDTO> getAllAuthors() {
        List<AuthorDTO> authors =
                AuthorDTO.convertToDTO(authorService.getAllAuthors());
        return authors;
    }

    @GetMapping("getById")
    public AuthorDTO getById(@RequestParam Long id) {
        return AuthorDTO.convertToDTO(authorService.getById(id));
    }

    @PutMapping("update")
    public AuthorDTO updateAuthor(@Valid @RequestBody AuthorDTO dto) throws Exception {
        return AuthorDTO.convertToDTO(authorService.updateAuthor(
                dto.getAuthorId(),
                dto.getAuthorName(),
                dto.getAuthorNationality(),
                dto.getAuthorBiography()));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteAuthor(@RequestParam Long id) {
        return authorService.deleteById(id);
    }
}
