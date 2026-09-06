package com.mini.project.services;

import com.mini.project.entities.Author;
import com.mini.project.entities.Reservation;
import com.mini.project.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    ReservationRepository reservationRepository;
    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    //Add service
    public Long addReservation(Date reservationDate, String status){
        Reservation reservation =  new Reservation();
        reservation.setIsActive(true);
        reservation.setCreatedDate(new Date());
        reservation.setReservationDate(reservationDate);
        reservation.setStatus(status);
        reservation = reservationRepository.save(reservation);
        return reservation.getId();
    }

    //Get All Reservations service
    public List<Reservation> getAllReservations() {
        return reservationRepository.getAllReservations();
    }

    //Get Reservation By Id service
    public Reservation getById(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent() && reservation.get().getIsActive()) {
            return reservation.get();
        }
        return new Reservation();
    }

    //Update service
    public Reservation updateReservation(Long id, Date updateReservationDate, String updateStatus) throws Exception{
        Reservation reservationToUpdate =  reservationRepository.getById(id);
        if(reservationToUpdate==null){
            throw new Exception("Reservation is not found by the id");
        }
        reservationToUpdate.setUpdatedDate(new Date());
        reservationToUpdate.setReservationDate(updateReservationDate);
        reservationToUpdate.setStatus(updateStatus);
        reservationToUpdate = reservationRepository.save(reservationToUpdate);
        return reservationToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id){
        Reservation deleteReservation = reservationRepository.getById(id);
        if(deleteReservation == null){
            return false;
        }
        deleteReservation.setIsActive(false);
        deleteReservation.setUpdatedDate(new Date());
        reservationRepository.save(deleteReservation);
        return true;
    }
}
