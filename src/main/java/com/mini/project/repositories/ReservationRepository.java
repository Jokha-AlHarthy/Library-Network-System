package com.mini.project.repositories;

import com.mini.project.entities.Author;
import com.mini.project.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.isActive=true")
    List<Reservation> getAllReservations();

    @Query("SELECT r FROM Reservation r WHERE r.isActive=true AND r.id=:reservation")
    Reservation getById(@Param("reservation") Long id);
}
