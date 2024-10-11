package ru.ershov.project.kitchenservice.mappers;

public interface ToDTOMapper<E, D> {

    D toDTO(E entity);

}
