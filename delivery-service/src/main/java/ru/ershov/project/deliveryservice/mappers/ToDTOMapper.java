package ru.ershov.project.deliveryservice.mappers;

public interface ToDTOMapper<E, D> {

    D toDTO(E entity);

}
