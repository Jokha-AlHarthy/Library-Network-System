package com.mini.project.dto;

import com.mini.project.entities.Fine;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
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
public class FineDTO {
    @Positive
    private Long fineId;

    @Positive(message = "Member ID must be positive")
    private Long memberId;

    @Positive(message = "Loan ID must be positive")
    private Long loanId;

    @Positive(message = "Fine amount must be positive")
    private Double amount;

    @NotBlank(message = "Fine reason cannot be blank")
    @Size(min = 3, max = 200, message = "Fine reason has to be between 3 and 200 characters")
    private String reason;

    @NotBlank(message = "Fine status cannot be blank")
    @Size(min = 2, max = 30, message = "Fine status has to be between 2 and 30 characters")
    private String status;

    @PastOrPresent(message = "Issued date cannot be in the future")
    private Date issuedDate;

    public static FineDTO convertToDTO(Fine entity) {
        FineDTO dto = FineDTO.builder()
                .fineId(entity.getId())
                .memberId(entity.getMember() != null ? entity.getMember().getId() : null)
                .loanId(entity.getLoan() != null ? entity.getLoan().getId() : null)
                .amount(entity.getAmount())
                .reason(entity.getReason())
                .status(entity.getStatus())
                .issuedDate(entity.getIssuedDate())
                .build();
        return dto;
    }

    public static List<FineDTO> convertToDTO(List<Fine> entityList) {
        List<FineDTO> dtos = new ArrayList<>();
        for (Fine f : entityList) {
            dtos.add(convertToDTO(f));
        }
        return dtos;
    }
}