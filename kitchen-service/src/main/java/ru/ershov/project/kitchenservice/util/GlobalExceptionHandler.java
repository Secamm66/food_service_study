package ru.ershov.project.kitchenservice.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({InvalidPageParameterException.class, EntityNotCreatedException.class})
    private ResponseEntity<OrderErrorResponse> handleException(Exception e) {
        OrderErrorResponse orderErrorResponse = new OrderErrorResponse();
        orderErrorResponse.setStatus(400).setError("Bad Request").setMessage(e.getMessage()).setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(orderErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<OrderErrorResponse> handleException(EntityNotFoundException e) {
        OrderErrorResponse orderErrorResponse = new OrderErrorResponse();
        orderErrorResponse.setStatus(404).setError("Not Found").setMessage(e.getMessage()).setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(orderErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<OrderErrorResponse> handleOtherException(Exception e) {
        OrderErrorResponse orderErrorResponse = new OrderErrorResponse();
        orderErrorResponse.setStatus(500).setError("Internal Server Error").setMessage(e.getMessage()).setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(orderErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}