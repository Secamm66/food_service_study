package ru.ershov.project.deliveryservice.util;

public class EntityNotCreatedException extends RuntimeException {
    public EntityNotCreatedException(String message) {
        super (message);
    }
}
