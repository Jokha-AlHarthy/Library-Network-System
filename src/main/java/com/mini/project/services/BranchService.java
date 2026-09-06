package com.mini.project.services;

import com.mini.project.entities.Author;
import com.mini.project.entities.Branch;
import com.mini.project.repositories.AuthorRepository;
import com.mini.project.repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BranchService {
    BranchRepository branchRepository;
    @Autowired
    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    //Add service
    public Long addBranch(String name, String location){
        Branch branch =  new Branch();
        branch.setIsActive(true);
        branch.setCreatedDate(new Date());
        branch.setName(name);
        branch.setLocation(location);
        branch = branchRepository.save(branch);
        return branch.getId();
    }

    //Get All branches service
    public List<Branch> getAllBranches() {
        return branchRepository.getAllBranches();
    }

    //Get Branch By Id service
    public Branch getById(Long id) {
        Optional<Branch> branch = branchRepository.findById(id);
        if (branch.isPresent() && branch.get().getIsActive()) {
            return branch.get();
        }
        return new Branch();
    }

    //Update service
    public Branch updateBranch(Long id, String updateName, String updateLocation) throws Exception{
        Branch branchToUpdate =  branchRepository.getById(id);
        if(branchToUpdate==null){
            throw new Exception("Branch is not found by the id");
        }
        branchToUpdate.setUpdatedDate(new Date());
        branchToUpdate.setName(updateName);
        branchToUpdate.setLocation(updateLocation);
        branchToUpdate = branchRepository.save(branchToUpdate);
        return branchToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id){
        Branch deleteBranch = branchRepository.getById(id);
        if(deleteBranch == null){
            return false;
        }
        deleteBranch.setIsActive(false);
        deleteBranch.setUpdatedDate(new Date());
        branchRepository.save(deleteBranch);
        return true;
    }
}
