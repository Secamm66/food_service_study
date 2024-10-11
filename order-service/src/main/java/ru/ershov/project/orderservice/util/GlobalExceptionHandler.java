package ru.ershov.project.orderservice.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.ershov.project.orderservice.dto.OrderListGetResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<OrderListGetResponse> handleException(InvalidPageParameterException e) {
        OrderListGetResponse response = new OrderListGetResponse();
        response.setOrders(new ArrayList<>());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<OrderErrorResponse> handleException(EntityNotFoundException e) {
        OrderErrorResponse orderErrorResponse = new OrderErrorResponse();
        orderErrorResponse.setStatus(404).setError("Not Found").setMessage(e.getMessage()).setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(orderErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<OrderErrorResponse> handleException(RestaurantIsNotOpenException e) {
        OrderErrorResponse orderErrorResponse = new OrderErrorResponse();
        orderErrorResponse.setStatus(409).setError("Conflict").setMessage(e.getMessage()).setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(orderErrorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    private ResponseEntity<OrderErrorResponse> handleException(Exception e) {
        OrderErrorResponse orderErrorResponse = new OrderErrorResponse();
        orderErrorResponse.setStatus(500).setError("Internal Server Error").setMessage(e.getMessage()).setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(orderErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
