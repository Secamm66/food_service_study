package ru.ershov.project.orderservice.mapper;

public interface ToDTOMapper<E, D> {

    D toDTO(E entity);

}