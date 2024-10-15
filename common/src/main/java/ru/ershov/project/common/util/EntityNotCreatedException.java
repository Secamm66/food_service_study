package ru.ershov.project.common.util;

public class EntityNotCreatedException extends RuntimeException {
    public EntityNotCreatedException(String message) {
        super (message);
    }
}
