package com.mini.project.dto;

import com.mini.project.entities.Review;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ReviewDTO {
    @Positive
    private Long reviewId;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private Integer rating;

    @NotBlank(message = "Review comment cannot be blank")
    @Size(min = 2, max = 500, message = "Review comment has to be between 2 and 500 characters")
    private String comment;

    private Date reviewDate;


    public static ReviewDTO convertToDTO(Review entity) {
        ReviewDTO dto = ReviewDTO.builder()
                .reviewId(entity.getId())
                .rating(entity.getRating())
                .comment(entity.getComment())
                .reviewDate(entity.getReviewDate())
                .build();
        return dto;
    }

    public static List<ReviewDTO> convertToDTO(List<Review> entityList) {
        List<ReviewDTO> dtos = new ArrayList<>();
        for (Review r : entityList) {
            dtos.add(convertToDTO(r));
        }
        return dtos;
    }
}
