package com.mini.project.repositories;

import com.mini.project.entities.Author;
import com.mini.project.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    @Query("SELECT p FROM Publisher p WHERE p.isActive=true")
    List<Publisher> getAllPublishers();

    @Query("SELECT p FROM Publisher p WHERE p.isActive=true AND p.id=:publisher")
    Publisher getById(@Param("publisher") Long id);
}
