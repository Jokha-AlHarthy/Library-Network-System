package com.mini.project.services;

import com.mini.project.entities.Author;
import com.mini.project.entities.Loan;
import com.mini.project.repositories.AuthorRepository;
import com.mini.project.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    LoanRepository loanRepository;
    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
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
}
