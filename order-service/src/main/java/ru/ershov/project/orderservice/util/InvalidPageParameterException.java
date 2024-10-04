package ru.ershov.project.orderservice.util;

public class InvalidPageParameterException extends RuntimeException {
    public InvalidPageParameterException(String message) {
        super(message);
    }
}
