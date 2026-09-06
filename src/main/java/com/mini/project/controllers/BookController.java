package com.mini.project.controllers;

import com.mini.project.dto.BookDTO;
import com.mini.project.entities.Book;
import com.mini.project.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {
    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("add")
    public Long addBook(@Valid @RequestBody BookDTO dto) {
        return bookService.addBook(
                dto.getBookTitle(),
                dto.getBookIsbn(),
                dto.getTotalCopies(),
                dto.getAvailableCopies());
    }

    @GetMapping("getAll")
    public List<BookDTO> getAllBooks() {
        List<BookDTO> books = BookDTO.convertToDTO(bookService.getAllBooks());
        return books;
    }

    @GetMapping("getById")
    public BookDTO getById(@RequestParam Long id) {
        return BookDTO.convertToDTO(bookService.getById(id));
    }

    @GetMapping("byAuthor")
    public List<BookDTO> getBooksByAuthor(@RequestParam Long authorId) {
        return BookDTO.convertToDTO(bookService.getBooksByAuthor(authorId)
        );
    }

    @GetMapping("zeroCopies")
    public List<BookDTO> getZeroCopies() {
        return BookDTO.convertToDTO(bookService.getZeroCopies()
        );
    }

    @PutMapping("update")
    public BookDTO updateBook(@Valid @RequestBody BookDTO dto) throws Exception {
        return BookDTO.convertToDTO(bookService.updateBook(
                dto.getBookId(),
                dto.getBookTitle(),
                dto.getBookIsbn(),
                dto.getTotalCopies(),
                dto.getAvailableCopies()));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteBook(@RequestParam Long id) {
        return bookService.deleteById(id);
    }
}
