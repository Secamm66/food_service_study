package ru.ershov.project.kitchenservice.mappers;

public interface ToEntityMapper<E, D> {

    E toEntity(D dto);

}
