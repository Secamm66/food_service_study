package ru.ershov.project.orderservice.mapper;

public interface ToEntityMapper<E, D> {

    E toEntity(D dto);

}