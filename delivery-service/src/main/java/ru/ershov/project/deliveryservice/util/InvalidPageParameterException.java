package ru.ershov.project.deliveryservice.util;

public class InvalidPageParameterException extends RuntimeException {
    public InvalidPageParameterException(String message) {
        super(message);
    }
}
