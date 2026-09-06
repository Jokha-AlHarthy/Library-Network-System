package com.mini.project.controllers;

import com.mini.project.entities.Author;
import com.mini.project.services.AuthorService;
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
    public Long addAuthor(@RequestParam String name,
                          @RequestParam String nationality,
                          @RequestParam String biography) {
        return authorService.addAuthor(name, nationality, biography);
    }

    @GetMapping("getAll")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("getById")
    public Author getById(@RequestParam Long id) {
        return authorService.getById(id);
    }

    @PutMapping("update")
    public Author updateAuthor(@RequestParam Long id,
                               @RequestParam String updateName,
                               @RequestParam String updateNationality,
                               @RequestParam String updateBiography) throws Exception {
        return authorService.updateAuthor(id, updateName, updateNationality, updateBiography);
    }

    @DeleteMapping("deleteById")
    public Boolean deleteAuthor(@RequestParam Long id) {
        return authorService.deleteById(id);
    }
}
