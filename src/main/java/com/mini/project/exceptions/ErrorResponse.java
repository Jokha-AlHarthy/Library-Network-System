package com.mini.project.exceptions;

import java.time.LocalDateTime;
import java.util.Date;

public record ErrorResponse(
        int status,
        String error,
        String message,
        LocalDateTime timestamp
) {
}
