package com.mini.project.controllers;

import com.mini.project.dto.FineDTO;
import com.mini.project.entities.Fine;
import com.mini.project.services.FineService;
import jakarta.validation.Valid;
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
    public Long addFine(@Valid @RequestBody FineDTO dto) {
        return fineService.addFine(
                dto.getAmount(),
                dto.getReason(),
                dto.getStatus(),
                dto.getIssuedDate());
    }

    @GetMapping("getAll")
    public List<FineDTO> getAllFines() {
        List<FineDTO> fines = FineDTO.convertToDTO(fineService.getAllFines());
        return fines;
    }

    @GetMapping("getById")
    public FineDTO getById(@RequestParam Long id) {
        return FineDTO.convertToDTO(fineService.getById(id));
    }

    @GetMapping("unpaid")
    public List<FineDTO> getUnpaidFines() {
        return FineDTO.convertToDTO(fineService.getUnpaidFines());
    }

    @GetMapping("memberStats")
    public List<Object[]> getMemberFines() {
        return fineService.getMemberFines();
    }

    @PutMapping("update")
    public FineDTO updateFine(@Valid @RequestBody FineDTO dto) throws Exception {
        return FineDTO.convertToDTO(fineService.updateFine(
                dto.getFineId(),
                dto.getAmount(),
                dto.getReason(),
                dto.getStatus(),
                dto.getIssuedDate()));
    }

    @PutMapping("pay")
    public Boolean payFine(@RequestParam Long id) throws Exception {
        return fineService.payFine(id);
    }

    @DeleteMapping("deleteById")
    public Boolean deleteFine(@RequestParam Long id) {
        return fineService.deleteById(id);
    }
}
