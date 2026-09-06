package com.mini.project.dto;

import com.mini.project.entities.Author;
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
public class AuthorDTO {
    @Positive
    private Long authorId;

    @NotBlank(message = "Author name cannot be blank")
    @Size(min = 3, max = 50, message = "Author name has to be between 3 and 50 characters")
    private String authorName;

    @NotBlank(message = "Author nationality cannot be blank")
    @Size(min = 3, max = 30, message = "Author nationality has to be between 3 and 30 characters")
    private String authorNationality;

    @NotBlank(message = "Author biography cannot be blank")
    @Size(min = 10, max = 500, message = "Author biography has to be between 10 and 500 characters")
    private String authorBiography;

    public static AuthorDTO convertToDTO(Author entity) {
        AuthorDTO dto = AuthorDTO.builder()
                .authorId(entity.getId())
                .authorName(entity.getName())
                .authorNationality(entity.getNationality())
                .authorBiography(entity.getBiography())
                .build();
        return dto;
    }

    public static List<AuthorDTO> convertToDTO(List<Author> entityList) {
        List<AuthorDTO> dtos = new ArrayList<>();
        for (Author a : entityList) {
            dtos.add(convertToDTO(a));
        }
        return dtos;
    }
}
