package ru.ershov.project.orderservice.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderErrorResponse {
    private int status;
    private String error;
    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    public OrderErrorResponse(int status, String error, String message, LocalDateTime timestamp) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;
    }
}