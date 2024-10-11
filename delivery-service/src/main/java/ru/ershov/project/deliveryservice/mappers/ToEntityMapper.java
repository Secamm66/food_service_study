package ru.ershov.project.deliveryservice.mappers;

public interface ToEntityMapper<E, D> {

    E toEntity(D dto);

}
