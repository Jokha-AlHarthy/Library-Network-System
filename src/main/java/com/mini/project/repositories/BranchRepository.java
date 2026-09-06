package com.mini.project.repositories;

import com.mini.project.entities.Author;
import com.mini.project.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    @Query("SELECT b FROM Branch b WHERE b.isActive=true")
    List<Branch> getAllBranches();

    @Query("SELECT b FROM Branch b WHERE b.isActive=true AND b.id=:branch")
    Branch getById(@Param("branch") Long id);
}
