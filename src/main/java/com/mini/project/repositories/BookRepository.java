package com.mini.project.repositories;

import com.mini.project.entities.Author;
import com.mini.project.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.isActive=true")
    List<Book> getAllBooks();

    @Query("SELECT b FROM Book b WHERE b.isActive=true AND b.id=:book")
    Book getById(@Param("book") Long id);
}
