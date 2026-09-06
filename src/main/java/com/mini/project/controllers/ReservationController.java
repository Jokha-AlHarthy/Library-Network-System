package com.mini.project.controllers;

import com.mini.project.dto.ReservationDTO;
import com.mini.project.entities.Reservation;
import com.mini.project.services.ReservationService;
import jakarta.validation.Valid;
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
    public Long addReservation(@Valid @RequestBody ReservationDTO dto) {
        return reservationService.addReservation(
                dto.getReservationDate(),
                dto.getStatus());
    }

    @GetMapping("getAll")
    public List<ReservationDTO> getAllReservations() {
        List<ReservationDTO> reservations = ReservationDTO.convertToDTO(reservationService.getAllReservations());
        return reservations;
    }

    @GetMapping("getById")
    public ReservationDTO getById(@RequestParam Long id) {
        return ReservationDTO.convertToDTO(reservationService.getById(id));
    }

    @PutMapping("update")
    public ReservationDTO updateReservation(@Valid @RequestBody ReservationDTO dto) throws Exception {
        return ReservationDTO.convertToDTO(reservationService.updateReservation(
                dto.getReservationId(),
                dto.getReservationDate(),
                dto.getStatus()));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteReservation(@RequestParam Long id) {
        return reservationService.deleteById(id);
    }

    @PostMapping("reserve")
    public Long reserveBook(@RequestParam Long memberId, @RequestParam Long bookId) throws Exception {

        return reservationService.reserveBook(memberId, bookId);
    }
}
