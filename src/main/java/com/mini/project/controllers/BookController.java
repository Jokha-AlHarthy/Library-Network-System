package com.mini.project.controllers;

import com.mini.project.entities.Book;
import com.mini.project.services.BookService;
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
    public Long addBook(@RequestParam String title,
                        @RequestParam String isbn,
                        @RequestParam Integer totalCopies,
                        @RequestParam Integer availableCopies) {
        return bookService.addBook(title, isbn, totalCopies, availableCopies);
    }

    @GetMapping("getAll")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("getById")
    public Book getById(@RequestParam Long id) {
        return bookService.getById(id);
    }

    @PutMapping("update")
    public Book updateBook(@RequestParam Long id,
                           @RequestParam String updateTitle,
                           @RequestParam String updateIsbn,
                           @RequestParam Integer updateTotalCopies,
                           @RequestParam Integer updateAvailableCopies) throws Exception {
        return bookService.updateBook(id, updateTitle, updateIsbn,
                updateTotalCopies, updateAvailableCopies);
    }

    @DeleteMapping("deleteById")
    public Boolean deleteBook(@RequestParam Long id) {
        return bookService.deleteById(id);
    }
}
