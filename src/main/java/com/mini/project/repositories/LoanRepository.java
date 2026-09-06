package com.mini.project.repositories;

import com.mini.project.entities.Author;
import com.mini.project.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("SELECT l FROM Loan l WHERE l.isActive=true")
    List<Loan> getAllLoans();

    @Query("SELECT l FROM Loan l WHERE l.isActive=true AND l.id=:loan")
    Loan getById(@Param("loan") Long id);
}
