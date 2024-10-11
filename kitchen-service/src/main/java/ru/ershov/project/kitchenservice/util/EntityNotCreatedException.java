package ru.ershov.project.kitchenservice.util;

public class EntityNotCreatedException extends RuntimeException {
    public EntityNotCreatedException(String message) {
        super (message);
    }
}
