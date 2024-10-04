package ru.ershov.project.orderservice.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<OrderErrorResponse> handleException(InvalidPageParameterException e) {
        OrderErrorResponse orderErrorResponse =
                new OrderErrorResponse(400, "Bad Request", e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(orderErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<OrderErrorResponse> handleException(EntityNotFoundException e) {
        OrderErrorResponse orderErrorResponse =
                new OrderErrorResponse(404, "Not Found", e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(orderErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<OrderErrorResponse> handleException(Exception e) {
        OrderErrorResponse orderErrorResponse =
                new OrderErrorResponse(500, "Internal Server Error", e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(orderErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
