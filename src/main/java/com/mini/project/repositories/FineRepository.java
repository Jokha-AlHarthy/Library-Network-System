package com.mini.project.repositories;

import com.mini.project.entities.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FineRepository extends JpaRepository<Fine, Long> {
    @Query("SELECT f FROM Fine f WHERE f.isActive=true")
    List<Fine> getAllFines();

    @Query("SELECT f FROM Fine f WHERE f.isActive=true AND f.id=:fine")
    Fine getById(@Param("fine") Long id);
}
