package ru.ershov.project.orderservice.util;

public class RestaurantIsNotOpenException extends RuntimeException {
    public RestaurantIsNotOpenException(String message) {
        super(message);
    }
}
