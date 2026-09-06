package com.mini.project.services;

import com.mini.project.entities.Author;
import com.mini.project.entities.Staff;
import com.mini.project.repositories.AuthorRepository;
import com.mini.project.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    StaffRepository staffRepository;
    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    //Add service
    public Long addStaff(String name, String role, String phoneNumber){
        Staff staff =  new Staff();
        staff.setIsActive(true);
        staff.setCreatedDate(new Date());
        staff.setName(name);
        staff.setRole(role);
        staff.setPhoneNumber(phoneNumber);
        staff = staffRepository.save(staff);
        return staff.getId();
    }

    //Get All Staffs service
    public List<Staff> getAllStaffs() {
        return staffRepository.getAllStaffs();
    }

    //Get Staff By Id service
    public Staff getById(Long id) {
        Optional<Staff> staff = staffRepository.findById(id);
        if (staff.isPresent() && staff.get().getIsActive()) {
            return staff.get();
        }
        return new Staff();
    }

    //Update service
    public Staff updateStaff(Long id, String updateName, String updateRole, String updatePhoneNumber) throws Exception{
        Staff staffToUpdate =  staffRepository.getById(id);
        if(staffToUpdate==null){
            throw new Exception("Staff is not found by the id");
        }
        staffToUpdate.setUpdatedDate(new Date());
        staffToUpdate.setName(updateName);
        staffToUpdate.setRole(updateRole);
        staffToUpdate.setPhoneNumber(updatePhoneNumber);
        staffToUpdate = staffRepository.save(staffToUpdate);
        return staffToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id){
        Staff deleteStaff = staffRepository.getById(id);
        if(deleteStaff == null){
            return false;
        }
        deleteStaff.setIsActive(false);
        deleteStaff.setUpdatedDate(new Date());
        staffRepository.save(deleteStaff);
        return true;
    }
}
