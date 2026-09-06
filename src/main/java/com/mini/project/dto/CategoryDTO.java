package com.mini.project.dto;

import com.mini.project.entities.Category;
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
public class CategoryDTO {
    @Positive
    private Long categoryId;

    @NotBlank(message = "Category name cannot be blank")
    @Size(min = 2, max = 50, message = "Category name has to be between 2 and 50 characters")
    private String categoryName;

    @NotBlank(message = "Category description cannot be blank")
    @Size(min = 5, max = 200, message = "Category description has to be between 5 and 200 characters")
    private String categoryDescription;


    public static CategoryDTO convertToDTO(Category entity) {
        CategoryDTO dto = CategoryDTO.builder()
                .categoryId(entity.getId())
                .categoryName(entity.getName())
                .categoryDescription(entity.getDescription())
                .build();
        return dto;
    }

    public static List<CategoryDTO> convertToDTO(List<Category> entityList) {
        List<CategoryDTO> dtos = new ArrayList<>();
        for (Category c : entityList) {
            dtos.add(convertToDTO(c));
        }
        return dtos;
    }
}
