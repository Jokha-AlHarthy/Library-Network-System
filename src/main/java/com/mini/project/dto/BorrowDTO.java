package com.mini.project.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BorrowDTO {
    @NotNull
    private Long memberId;

    @NotNull
    private Long bookId;
}
