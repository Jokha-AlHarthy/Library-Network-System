package com.mini.project.controllers;

import com.mini.project.dto.BranchDTO;
import com.mini.project.entities.Branch;
import com.mini.project.services.BranchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("branch")
public class BranchController {
    BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("add")
    public Long addBranch(@Valid @RequestBody BranchDTO dto) {
        return branchService.addBranch(
                dto.getBranchName(),
                dto.getBranchLocation());
    }

    @GetMapping("getAll")
    public List<BranchDTO> getAllBranches() {
        List<BranchDTO> branches = BranchDTO.convertToDTO(branchService.getAllBranches());
        return branches;
    }

    @GetMapping("getById")
    public BranchDTO getById(@RequestParam Long id) {
        return BranchDTO.convertToDTO(branchService.getById(id));
    }

    @GetMapping("stats")
    public List<Object[]> getBranchStats() {
        return branchService.getBranchStats();
    }

    @PutMapping("update")
    public BranchDTO updateBranch(@Valid @RequestBody BranchDTO dto) throws Exception {
        return BranchDTO.convertToDTO(branchService.updateBranch(
                dto.getBranchId(),
                dto.getBranchName(),
                dto.getBranchLocation()));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteBranch(@RequestParam Long id) {
        return branchService.deleteById(id);
    }
}
