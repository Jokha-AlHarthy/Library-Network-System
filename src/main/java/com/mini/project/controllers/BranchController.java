package com.mini.project.controllers;

import com.mini.project.entities.Branch;
import com.mini.project.services.BranchService;
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
    public Long addBranch(@RequestParam String name,
                          @RequestParam String location) {
        return branchService.addBranch(name, location);
    }

    @GetMapping("getAll")
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    @GetMapping("getById")
    public Branch getById(@RequestParam Long id) {
        return branchService.getById(id);
    }

    @PutMapping("update")
    public Branch updateBranch(@RequestParam Long id,
                               @RequestParam String updateName,
                               @RequestParam String updateLocation) throws Exception {
        return branchService.updateBranch(id, updateName, updateLocation);
    }

    @DeleteMapping("deleteById")
    public Boolean deleteBranch(@RequestParam Long id) {
        return branchService.deleteById(id);
    }
}
