package com.mini.project.dto;

import com.mini.project.entities.Branch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class BranchDTO {
    @Positive
    private Long branchId;

    @NotBlank(message = "Branch name cannot be blank")
    @Size(min = 3, max = 50, message = "Branch name has to be between 3 and 50 characters")
    private String branchName;

    @NotBlank(message = "Branch location cannot be blank")
    @Size(min = 3, max = 100, message = "Branch location has to be between 3 and 100 characters")
    private String branchLocation;


    public static BranchDTO convertToDTO(Branch entity) {
        BranchDTO dto = BranchDTO.builder()
                .branchId(entity.getId())
                .branchName(entity.getName())
                .branchLocation(entity.getLocation())
                .build();
        return dto;
    }

    public static List<BranchDTO> convertToDTO(List<Branch> entityList) {
        List<BranchDTO> dtos = new ArrayList<>();
        for (Branch b : entityList) {
            dtos.add(convertToDTO(b));
        }
        return dtos;
    }
}
