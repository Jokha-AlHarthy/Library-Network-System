package com.mini.project.dto;

import com.mini.project.entities.Reservation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ReservationDTO {
    @Positive
    private Long reservationId;

    private Date reservationDate;

    @NotBlank(message = "Reservation status cannot be blank")
    @Size(min = 2, max = 30, message = "Reservation status has to be between 2 and 30 characters")
    private String status;


    public static ReservationDTO convertToDTO(Reservation entity) {
        ReservationDTO dto = ReservationDTO.builder()
                .reservationId(entity.getId())
                .reservationDate(entity.getReservationDate())
                .status(entity.getStatus())
                .build();
        return dto;
    }

    public static List<ReservationDTO> convertToDTO(List<Reservation> entityList) {
        List<ReservationDTO> dtos = new ArrayList<>();
        for (Reservation r : entityList) {
            dtos.add(convertToDTO(r));
        }
        return dtos;
    }
}
