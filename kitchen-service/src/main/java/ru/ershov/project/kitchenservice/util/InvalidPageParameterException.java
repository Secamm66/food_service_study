package ru.ershov.project.kitchenservice.util;

public class InvalidPageParameterException extends RuntimeException {
    public InvalidPageParameterException(String message) {
        super(message);
    }
}
