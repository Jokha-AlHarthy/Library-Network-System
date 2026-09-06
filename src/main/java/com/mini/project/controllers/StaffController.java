package com.mini.project.controllers;

import com.mini.project.entities.Staff;
import com.mini.project.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("staff")
public class StaffController {
    StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("add")
    public Long addStaff(@RequestParam String name,
                         @RequestParam String role,
                         @RequestParam String phoneNumber) {
        return staffService.addStaff(name, role, phoneNumber);
    }

    @GetMapping("getAll")
    public List<Staff> getAllStaffs() {
        return staffService.getAllStaffs();
    }

    @GetMapping("getById")
    public Staff getById(@RequestParam Long id) {
        return staffService.getById(id);
    }

    @PutMapping("update")
    public Staff updateStaff(@RequestParam Long id,
                             @RequestParam String updateName,
                             @RequestParam String updateRole,
                             @RequestParam String updatePhoneNumber) throws Exception {
        return staffService.updateStaff(id, updateName,
                updateRole, updatePhoneNumber);
    }

    @DeleteMapping("deleteById")
    public Boolean deleteStaff(@RequestParam Long id) {
        return staffService.deleteById(id);
    }
}
