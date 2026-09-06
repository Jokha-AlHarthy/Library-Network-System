package com.mini.project.controllers;

import com.mini.project.entities.Loan;
import com.mini.project.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("loan")
public class LoanController {
    LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("add")
    public Long addLoan(@RequestParam Date loanDate,
                        @RequestParam Date dueDate,
                        @RequestParam Date returnDate,
                        @RequestParam Boolean isReturned) {
        return loanService.addLoan(loanDate, dueDate, returnDate, isReturned);
    }

    @GetMapping("getAll")
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("getById")
    public Loan getById(@RequestParam Long id) {
        return loanService.getById(id);
    }

    @PutMapping("update")
    public Loan updateLoan(@RequestParam Long id,
                           @RequestParam Date updateLoanDate,
                           @RequestParam Date updateDueDate,
                           @RequestParam Date updateReturnDate,
                           @RequestParam Boolean updateIsReturned) throws Exception {
        return loanService.updateLoan(id, updateLoanDate, updateDueDate,
                updateReturnDate, updateIsReturned);
    }

    @DeleteMapping("deleteById")
    public Boolean deleteLoan(@RequestParam Long id) {
        return loanService.deleteById(id);
    }
}
