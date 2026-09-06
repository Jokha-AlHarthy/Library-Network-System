package com.mini.project.dto;

import com.mini.project.entities.Loan;
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

    private Date loanDate;

    private Date dueDate;

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
