package com.mini.project.dto;

import com.mini.project.entities.Staff;
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
public class StaffDTO {
    @Positive
    private Long staffId;

    @NotBlank(message = "Staff name cannot be blank")
    @Size(min = 3, max = 50, message = "Staff name has to be between 3 and 50 characters")
    private String staffName;

    @NotBlank(message = "Staff role cannot be blank")
    @Size(min = 2, max = 50, message = "Staff role has to be between 2 and 50 characters")
    private String staffRole;

    @NotBlank(message = "Phone number cannot be blank")
    private String staffPhoneNumber;


    public static StaffDTO convertToDTO(Staff entity) {
        StaffDTO dto = StaffDTO.builder()
                .staffId(entity.getId())
                .staffName(entity.getName())
                .staffRole(entity.getRole())
                .staffPhoneNumber(entity.getPhoneNumber())
                .build();
        return dto;
    }

    public static List<StaffDTO> convertToDTO(List<Staff> entityList) {
        List<StaffDTO> dtos = new ArrayList<>();
        for (Staff s : entityList) {
            dtos.add(convertToDTO(s));
        }
        return dtos;
    }
}
