package com.mini.project.services;

import com.mini.project.entities.*;
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
        return new Loan();
    }

    //Update service
    public Loan updateLoan(Long id, Date updateLoanDate, Date updateDueDate, Date updateReturnDate, Boolean updateIsReturned) throws Exception{
        Loan loanToUpdate =  loanRepository.getById(id);
        if(loanToUpdate==null){
            throw new Exception("Author is not found by the id");
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
        Loan deleteLoan = loanRepository.getById(id);
        if(deleteLoan == null){
            return false;
        }
        deleteLoan.setIsActive(false);
        deleteLoan.setUpdatedDate(new Date());
        loanRepository.save(deleteLoan);
        return true;
    }

    public Loan borrowBook(Long memberId, Long bookId) {

        // 1. Find member
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // 2. Check member is active
        if (!member.getIsActive()) {
            throw new RuntimeException("Member is inactive");
        }

        // 3. Find book
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // 4. Check book is active
        if (!book.getIsActive()) {
            throw new RuntimeException("Book is inactive");
        }

        // 5. Check available copies
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No copies available");
        }

        // 6. Check member's active loans
        long activeLoans =
                loanRepository.countActiveLoansByMember(memberId);

        if (activeLoans >= 5) {
            throw new RuntimeException(
                    "Member has too many active loans");
        }

        // 7. Create new Loan
        Loan loan = new Loan();

        loan.setMember(member);
        loan.setBook(book);

        loan.setLoanDate(new Date());

        // Due date = 14 days from today
        Date dueDate = new Date(
                System.currentTimeMillis()
                        + (14L * 24 * 60 * 60 * 1000)
        );

        loan.setDueDate(dueDate);

        loan.setReturnDate(null);
        loan.setIsReturned(false);

        loan.setIsActive(true);
        loan.setCreatedDate(new Date());

        // 8. Decrease available copies
        book.setAvailableCopies(
                book.getAvailableCopies() - 1
        );

        // 9. Save book
        bookRepository.save(book);

        // 10. Save loan
        return loanRepository.save(loan);
    }

    public Loan returnBook(Long loanId) throws Exception {
        Loan loan = loanRepository.findActiveLoan(loanId);
        if (loan == null) {
            throw new Exception("Loan is not found");
        }
        if (loan.getIsReturned()) {
            throw new Exception("Book is already returned");
        }
        Book book = loan.getBook();
        if (book == null) {
            throw new Exception("Book is not found");
        }
        // Return the book
        loan.setReturnDate(new Date());
        loan.setIsReturned(true);
        loan.setUpdatedDate(new Date());
        // Increase available copies
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        book.setUpdatedDate(new Date());
        bookRepository.save(book);
        // Check if the book is returned late
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
