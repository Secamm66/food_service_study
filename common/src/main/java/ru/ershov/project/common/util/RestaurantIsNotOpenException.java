package ru.ershov.project.common.util;

public class RestaurantIsNotOpenException extends RuntimeException {
    public RestaurantIsNotOpenException(String message) {
        super(message);
    }
}
