package com.mini.project.repositories;

import com.mini.project.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author a WHERE a.isActive=true")
    List<Author> getAllAuthors();

    @Query("SELECT a FROM Author a WHERE a.isActive=true AND a.id=:author")
    Author getById(@Param("author") Long id);
}
