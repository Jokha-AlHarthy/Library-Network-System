package com.mini.project.services;

import com.mini.project.entities.Author;
import com.mini.project.entities.Fine;
import com.mini.project.repositories.AuthorRepository;
import com.mini.project.repositories.FineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FineService {
    FineRepository fineRepository;
    @Autowired
    public FineService(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    //Add service
    public Long addFine(Double amount, String reason, String status, Date issuedDate){
        Fine fine =  new Fine();
        fine.setIsActive(true);
        fine.setCreatedDate(new Date());
        fine.setAmount(amount);
        fine.setReason(reason);
        fine.setStatus(status);
        fine.setIssuedDate(issuedDate);
        fine = fineRepository.save(fine);
        return fine.getId();
    }

    //Get All fines service
    public List<Fine> getAllFines() {
        return fineRepository.getAllFines();
    }

    //Get Fine By Id service
    public Fine getById(Long id) {
        Optional<Fine> fine = fineRepository.findById(id);
        if (fine.isPresent() && fine.get().getIsActive()) {
            return fine.get();
        }
        return new Fine();
    }

    //Update service
    public Fine updateFine(Long id, Double updateAmount, String updateReason, String updateStatus, Date updateIssuedDate) throws Exception{
        Fine fineToUpdate =  fineRepository.getById(id);
        if(fineToUpdate==null){
            throw new Exception("Fine is not found by the id");
        }
        fineToUpdate.setUpdatedDate(new Date());
        fineToUpdate.setAmount(updateAmount);
        fineToUpdate.setReason(updateReason);
        fineToUpdate.setStatus(updateStatus);
        fineToUpdate = fineRepository.save(fineToUpdate);
        return fineToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id){
        Fine deleteFine = fineRepository.getById(id);
        if(deleteFine == null){
            return false;
        }
        deleteFine.setIsActive(false);
        deleteFine.setUpdatedDate(new Date());
        fineRepository.save(deleteFine);
        return true;
    }

    public Boolean payFine(Long id) throws Exception {
        Fine fine = fineRepository.getById(id);
        if (fine == null) {
            throw new Exception("Fine not found");
        }
        fine.setStatus("PAID");
        fine.setUpdatedDate(new Date());
        fineRepository.save(fine);
        return true;
    }

    public List<Fine> getUnpaidFines() {
        return fineRepository.getUnpaidFines();
    }

    public List<Object[]> getMemberFines() {
        return fineRepository.getMemberFines();
    }
}
