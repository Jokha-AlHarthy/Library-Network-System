package com.mini.project.dto;

import com.mini.project.entities.Member;
import jakarta.validation.constraints.Email;
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
public class MemberDTO {
    @Positive
    private Long memberId;

    @NotBlank(message = "Member name cannot be blank")
    @Size(min = 3, max = 50, message = "Member name has to be between 3 and 50 characters")
    private String memberName;

    @NotBlank(message = "Member email cannot be blank")
    @Email(message = "Member email must be valid")
    private String memberEmail;

    @NotBlank(message = "Phone number cannot be blank")
    private String memberPhoneNumber;

    @NotBlank(message = "Membership type cannot be blank")
    private String membershipType;


    public static MemberDTO convertToDTO(Member entity) {
        MemberDTO dto = MemberDTO.builder()
                .memberId(entity.getId())
                .memberName(entity.getName())
                .memberEmail(entity.getEmail())
                .memberPhoneNumber(entity.getPhoneNumber())
                .membershipType(entity.getMembershipType())
                .build();
        return dto;
    }

    public static List<MemberDTO> convertToDTO(List<Member> entityList) {
        List<MemberDTO> dtos = new ArrayList<>();
        for (Member m : entityList) {
            dtos.add(convertToDTO(m));
        }
        return dtos;
    }
}
