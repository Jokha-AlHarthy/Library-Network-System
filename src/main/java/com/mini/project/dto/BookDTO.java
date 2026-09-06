package com.mini.project.dto;

import com.mini.project.entities.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class BookDTO {
    @Positive
    private Long bookId;

    @NotBlank(message = "Book title cannot be blank")
    @Size(min = 2, max = 100, message = "Book title has to be between 2 and 100 characters")
    private String bookTitle;

    @NotBlank(message = "ISBN cannot be blank")
    @Size(min = 10, max = 20, message = "ISBN has to be between 10 and 20 characters")
    private String bookIsbn;

    @PositiveOrZero
    private Integer totalCopies;

    @PositiveOrZero
    private Integer availableCopies;


    public static BookDTO convertToDTO(Book entity) {
        BookDTO dto = BookDTO.builder()
                .bookId(entity.getId())
                .bookTitle(entity.getTitle())
                .bookIsbn(entity.getIsbn())
                .totalCopies(entity.getTotalCopies())
                .availableCopies(entity.getAvailableCopies())
                .build();
        return dto;
    }

    public static List<BookDTO> convertToDTO(List<Book> entityList) {
        List<BookDTO> dtos = new ArrayList<>();
        for (Book b : entityList) {
            dtos.add(convertToDTO(b));
        }
        return dtos;
    }
}

