package com.mini.project.dto;

import com.mini.project.entities.Publisher;
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
public class PublisherDTO {
    @Positive
    private Long publisherId;

    @NotBlank(message = "Publisher name cannot be blank")
    @Size(min = 3, max = 100, message = "Publisher name has to be between 3 and 100 characters")
    private String publisherName;

    @NotBlank(message = "Publisher address cannot be blank")
    @Size(min = 5, max = 200, message = "Publisher address has to be between 5 and 200 characters")
    private String publisherAddress;

    @NotBlank(message = "Contact email cannot be blank")
    @Email(message = "Contact email must be valid")
    private String publisherContactEmail;


    public static PublisherDTO convertToDTO(Publisher entity) {
        PublisherDTO dto = PublisherDTO.builder()
                .publisherId(entity.getId())
                .publisherName(entity.getName())
                .publisherAddress(entity.getAddress())
                .publisherContactEmail(entity.getContactEmail())
                .build();
        return dto;
    }

    public static List<PublisherDTO> convertToDTO(List<Publisher> entityList) {
        List<PublisherDTO> dtos = new ArrayList<>();
        for (Publisher p : entityList) {
            dtos.add(convertToDTO(p));
        }
        return dtos;
    }
}
