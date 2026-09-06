package com.mini.project.controllers;

import com.mini.project.entities.Reservation;
import com.mini.project.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservationController {
    ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("add")
    public Long addReservation(@RequestParam Date reservationDate,
                               @RequestParam String status) {
        return reservationService.addReservation(reservationDate, status);
    }

    @GetMapping("getAll")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("getById")
    public Reservation getById(@RequestParam Long id) {
        return reservationService.getById(id);
    }

    @PutMapping("update")
    public Reservation updateReservation(@RequestParam Long id,
                                         @RequestParam Date updateReservationDate,
                                         @RequestParam String updateStatus) throws Exception {
        return reservationService.updateReservation(id,
                updateReservationDate, updateStatus);
    }

    @DeleteMapping("deleteById")
    public Boolean deleteReservation(@RequestParam Long id) {
        return reservationService.deleteById(id);
    }
}
