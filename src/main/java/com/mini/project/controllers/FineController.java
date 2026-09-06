package com.mini.project.controllers;

import com.mini.project.entities.Fine;
import com.mini.project.services.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("fine")
public class FineController {
    FineService fineService;

    @Autowired
    public FineController(FineService fineService) {
        this.fineService = fineService;
    }

    @PostMapping("add")
    public Long addFine(@RequestParam Double amount,
                        @RequestParam String reason,
                        @RequestParam String status,
                        @RequestParam Date issuedDate) {
        return fineService.addFine(amount, reason, status, issuedDate);
    }

    @GetMapping("getAll")
    public List<Fine> getAllFines() {
        return fineService.getAllFines();
    }

    @GetMapping("getById")
    public Fine getById(@RequestParam Long id) {
        return fineService.getById(id);
    }

    @PutMapping("update")
    public Fine updateFine(@RequestParam Long id,
                           @RequestParam Double updateAmount,
                           @RequestParam String updateReason,
                           @RequestParam String updateStatus,
                           @RequestParam Date updateIssuedDate) throws Exception {
        return fineService.updateFine(id, updateAmount, updateReason,
                updateStatus, updateIssuedDate);
    }

    @DeleteMapping("deleteById")
    public Boolean deleteFine(@RequestParam Long id) {
        return fineService.deleteById(id);
    }
}
