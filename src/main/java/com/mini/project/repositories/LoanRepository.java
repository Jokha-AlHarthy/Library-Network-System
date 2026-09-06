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

    @Query("""
            SELECT COUNT(l)
            FROM Loan l
            WHERE l.member.id = :memberId
            AND l.isReturned = false
            AND l.isActive = true
            """)
    long countActiveLoansByMember(@Param("memberId") Long memberId);

    @Query("""
        SELECT l FROM Loan l
        WHERE l.id = :loanId
        AND l.isActive = true
        """)
    Loan findActiveLoan(@Param("loanId") Long loanId);

    @Query("SELECT l FROM Loan l WHERE l.isActive=true AND l.isReturned=false")
    List<Loan> getActiveLoans();

    @Query("SELECT l FROM Loan l WHERE l.isActive=true AND l.isReturned=false AND l.dueDate < CURRENT_DATE")
    List<Loan> getOverdueLoans();

    @Query("""
       SELECT l.book.id, COUNT(l.id)
       FROM Loan l
       WHERE l.isActive=true
       GROUP BY l.book.id
       ORDER BY COUNT(l.id) DESC
       """)
    List<Object[]> getMostBorrowed();
}
