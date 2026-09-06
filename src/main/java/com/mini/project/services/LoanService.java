package com.mini.project.services;

import com.mini.project.entities.*;
import com.mini.project.exceptions.BusinessRuleException;
import com.mini.project.exceptions.ResourceNotFoundException;
import com.mini.project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    LoanRepository loanRepository;
    MemberRepository memberRepository;
    BookRepository bookRepository;
    FineRepository fineRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, MemberRepository memberRepository, BookRepository bookRepository, FineRepository fineRepository) {
        this.loanRepository = loanRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.fineRepository = fineRepository;
    }

    //Add service
    public Long addLoan(Date loanDate, Date dueDate, Date returnDate, Boolean isReturned){
        Loan loan =  new Loan();
        loan.setIsActive(true);
        loan.setCreatedDate(new Date());
        loan.setLoanDate(loanDate);
        loan.setDueDate(dueDate);
        loan.setReturnDate(returnDate);
        loan.setIsReturned(isReturned);
        loan = loanRepository.save(loan);
        return loan.getId();
    }

    //Get All loans service
    public List<Loan> getAllLoans() {
        return loanRepository.getAllLoans();
    }

    //Get Loan By Id service
    public Loan getById(Long id) {
        Optional<Loan> loan = loanRepository.findById(id);
        if (loan.isPresent() && loan.get().getIsActive()) {
            return loan.get();
        }
        throw new ResourceNotFoundException("Loan not found with id: " + id);
    }

    //Update service
    public Loan updateLoan(Long id, Date updateLoanDate, Date updateDueDate, Date updateReturnDate, Boolean updateIsReturned) throws Exception{
        Loan loanToUpdate = loanRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Loan not found with id: " + id));

        if (!loanToUpdate.getIsActive()) {
            throw new ResourceNotFoundException("Loan not found with id: " + id);
        }
        loanToUpdate.setUpdatedDate(new Date());
        loanToUpdate.setLoanDate(updateLoanDate);
        loanToUpdate.setDueDate(updateDueDate);
        loanToUpdate.setReturnDate(updateReturnDate);
        loanToUpdate = loanRepository.save(loanToUpdate);
        return loanToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id){
        Loan deleteLoan = loanRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Loan not found with id: " + id));

        if (!deleteLoan.getIsActive()) {
            throw new ResourceNotFoundException("Loan not found with id: " + id);
        }
        deleteLoan.setIsActive(false);
        deleteLoan.setUpdatedDate(new Date());
        loanRepository.save(deleteLoan);
        return true;
    }

    public Loan borrowBook(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Member not found with id: " + memberId));
        if (!member.getIsActive()) {
            throw new BusinessRuleException("Member is inactive");
        }
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found with id: " + bookId));
        if (!book.getIsActive()) {
            throw new BusinessRuleException("Book is inactive");
        }
        if (book.getAvailableCopies() <= 0) {
            throw new BusinessRuleException("No copies available");
        }
        long activeLoans =
                loanRepository.countActiveLoansByMember(memberId);
        if (activeLoans >= 5) {
            throw new BusinessRuleException(
                    "Member has too many active loans");
        }
        Loan loan = new Loan();
        loan.setMember(member);
        loan.setBook(book);
        loan.setLoanDate(new Date());
        Date dueDate = new Date(
                System.currentTimeMillis()
                        + (14L * 24 * 60 * 60 * 1000)
        );
        loan.setDueDate(dueDate);
        loan.setReturnDate(null);
        loan.setIsReturned(false);
        loan.setIsActive(true);
        loan.setCreatedDate(new Date());
        book.setAvailableCopies(
                book.getAvailableCopies() - 1
        );
        bookRepository.save(book);
        return loanRepository.save(loan);
    }

    public Loan returnBook(Long loanId) {
        Loan loan = loanRepository.findActiveLoan(loanId);
        if (loan == null) {
            throw new ResourceNotFoundException(
                    "Loan not found with id: " + loanId);
        }
        if (loan.getIsReturned()) {
            throw new BusinessRuleException(
                    "Book is already returned");
        }
        Book book = loan.getBook();
        if (book == null) {
            throw new ResourceNotFoundException(
                    "Book not found");
        }
        loan.setReturnDate(new Date());
        loan.setIsReturned(true);
        loan.setUpdatedDate(new Date());
        book.setAvailableCopies(
                book.getAvailableCopies() + 1
        );
        book.setUpdatedDate(new Date());
        bookRepository.save(book);
        Date today = new Date();
        if (today.after(loan.getDueDate())) {
            Fine fine = new Fine();
            fine.setAmount(5.0);
            fine.setReason("Late return");
            fine.setStatus("UNPAID");
            fine.setIssuedDate(new Date());
            fine.setMember(loan.getMember());
            fine.setLoan(loan);
            fine.setIsActive(true);
            fine.setCreatedDate(new Date());
            fineRepository.save(fine);
        }
        return loanRepository.save(loan);
    }

    public List<Loan> getActiveLoans() {
        return loanRepository.getActiveLoans();
    }

    public List<Loan> getOverdueLoans() {
        return loanRepository.getOverdueLoans();
    }

    public List<Object[]> getMostBorrowed() {
        return loanRepository.getMostBorrowed();
    }
}
