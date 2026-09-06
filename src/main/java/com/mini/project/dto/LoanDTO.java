package com.mini.project.dto;

import com.mini.project.entities.Loan;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class LoanDTO {
    @Positive
    private Long loanId;

    @PastOrPresent(message = "Loan date cannot be in the future")
    private Date loanDate;

    @Future(message = "Due date must be in the future")
    private Date dueDate;

    @PastOrPresent(message = "Return date cannot be in the future")
    private Date returnDate;

    private Boolean isReturned;


    public static LoanDTO convertToDTO(Loan entity) {
        LoanDTO dto = LoanDTO.builder()
                .loanId(entity.getId())
                .loanDate(entity.getLoanDate())
                .dueDate(entity.getDueDate())
                .returnDate(entity.getReturnDate())
                .isReturned(entity.getIsReturned())
                .build();
        return dto;
    }

    public static List<LoanDTO> convertToDTO(List<Loan> entityList) {
        List<LoanDTO> dtos = new ArrayList<>();
        for (Loan l : entityList) {
            dtos.add(convertToDTO(l));
        }
        return dtos;
    }
}
