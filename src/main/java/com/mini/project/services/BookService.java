package com.mini.project.services;

import com.mini.project.entities.Author;
import com.mini.project.entities.Book;
import com.mini.project.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //Add service
    public Long addBook(String title, String isbn, Integer totalCopies, Integer availableCopies){
        Book book =  new Book();
        book.setIsActive(true);
        book.setCreatedDate(new Date());
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setTotalCopies(totalCopies);
        book.setAvailableCopies(availableCopies);
        book = bookRepository.save(book);
        return book.getId();
    }

    //Get All books service
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    //Get Book By Id service
    public Book getById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent() && book.get().getIsActive()) {
            return book.get();
        }
        return new Book();
    }

    //Update service
    public Book updateBook(Long id, String updateTitle, String updateIsbn, Integer updateTotalCopies, Integer updateAvailableCopies) throws Exception{
        Book bookToUpdate =  bookRepository.getById(id);
        if(bookToUpdate==null){
            throw new Exception("Book is not found by the id");
        }
        bookToUpdate.setUpdatedDate(new Date());
        bookToUpdate.setTitle(updateTitle);
        bookToUpdate.setIsbn(updateIsbn);
        bookToUpdate.setTotalCopies(updateTotalCopies);
        bookToUpdate.setAvailableCopies(updateAvailableCopies);
        bookToUpdate = bookRepository.save(bookToUpdate);
        return bookToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id){
        Book deleteBook = bookRepository.getById(id);
        if(deleteBook == null){
            return false;
        }
        deleteBook.setIsActive(false);
        deleteBook.setUpdatedDate(new Date());
        bookRepository.save(deleteBook);
        return true;
    }
}
