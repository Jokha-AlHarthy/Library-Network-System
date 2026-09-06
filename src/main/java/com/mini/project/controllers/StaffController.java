package com.mini.project.controllers;

import com.mini.project.dto.StaffDTO;
import com.mini.project.entities.Staff;
import com.mini.project.services.StaffService;
import jakarta.validation.Valid;
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
    public Long addStaff(@Valid @RequestBody StaffDTO dto) {
        return staffService.addStaff(
                dto.getStaffName(),
                dto.getStaffRole(),
                dto.getStaffPhoneNumber());
    }

    @GetMapping("getAll")
    public List<StaffDTO> getAllStaffs() {
        List<StaffDTO> staffs = StaffDTO.convertToDTO(staffService.getAllStaffs());
        return staffs;
    }

    @GetMapping("getById")
    public StaffDTO getById(@RequestParam Long id) {
        return StaffDTO.convertToDTO(staffService.getById(id));
    }

    @PutMapping("update")
    public StaffDTO updateStaff(@Valid @RequestBody StaffDTO dto) throws Exception {
        return StaffDTO.convertToDTO(staffService.updateStaff(
                dto.getStaffId(),
                dto.getStaffName(),
                dto.getStaffRole(),
                dto.getStaffPhoneNumber()));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteStaff(@RequestParam Long id) {
        return staffService.deleteById(id);
    }
}
