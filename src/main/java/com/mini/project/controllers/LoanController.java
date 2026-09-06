package com.mini.project.controllers;

import com.mini.project.dto.BorrowDTO;
import com.mini.project.dto.LoanDTO;
import com.mini.project.entities.Loan;
import com.mini.project.services.LoanService;
import jakarta.validation.Valid;
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
    public Long addLoan(@Valid @RequestBody LoanDTO dto) {
        return loanService.addLoan(
                dto.getLoanDate(),
                dto.getDueDate(),
                dto.getReturnDate(),
                dto.getIsReturned());
    }

    @PostMapping("borrow")
    public LoanDTO borrowBook(
            @Valid @RequestBody BorrowDTO dto) {

        Loan loan = loanService.borrowBook(
                dto.getMemberId(),
                dto.getBookId()
        );

        return LoanDTO.convertToDTO(loan);
    }

    @GetMapping("getAll")
    public List<LoanDTO> getAllLoans() {
        List<LoanDTO> loans = LoanDTO.convertToDTO(loanService.getAllLoans());
        return loans;
    }

    @GetMapping("getById")
    public LoanDTO getById(@RequestParam Long id) {
        return LoanDTO.convertToDTO(loanService.getById(id));
    }

    @GetMapping("active")
    public List<LoanDTO> getActiveLoans() {
        return LoanDTO.convertToDTO(
                loanService.getActiveLoans()
        );
    }

    @GetMapping("overdue")
    public List<LoanDTO> getOverdueLoans() {
        return LoanDTO.convertToDTO(
                loanService.getOverdueLoans()
        );
    }

    @GetMapping("mostBorrowed")
    public List<Object[]> getMostBorrowed() {
        return loanService.getMostBorrowed();
    }

    @PutMapping("update")
    public LoanDTO updateLoan(@Valid @RequestBody LoanDTO dto) throws Exception {
        return LoanDTO.convertToDTO(loanService.updateLoan(
                dto.getLoanId(),
                dto.getLoanDate(),
                dto.getDueDate(),
                dto.getReturnDate(),
                dto.getIsReturned()));
    }

    @PutMapping("return")
    public LoanDTO returnBook(@RequestParam Long loanId) throws Exception {

        Loan loan = loanService.returnBook(loanId);

        return LoanDTO.convertToDTO(loan);
    }

    @DeleteMapping("deleteById")
    public Boolean deleteLoan(@RequestParam Long id) {
        return loanService.deleteById(id);
    }
}
