package com.mini.project.repositories;

import com.mini.project.entities.Branch;
import com.mini.project.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("SELECT c FROM Category c WHERE c.isActive=true")
    List<Category> getAllCategories();
    @Query("SELECT c FROM Category c WHERE c.isActive=true AND c.id=:category")
    Category getById(@Param("category") Long id);
}
