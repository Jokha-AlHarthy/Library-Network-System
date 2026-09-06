package com.mini.project.controllers;

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

    @GetMapping("getAll")
    public List<LoanDTO> getAllLoans() {
        List<LoanDTO> loans = LoanDTO.convertToDTO(loanService.getAllLoans());
        return loans;
    }

    @GetMapping("getById")
    public LoanDTO getById(@RequestParam Long id) {
        return LoanDTO.convertToDTO(loanService.getById(id));
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

    @DeleteMapping("deleteById")
    public Boolean deleteLoan(@RequestParam Long id) {
        return loanService.deleteById(id);
    }
}
