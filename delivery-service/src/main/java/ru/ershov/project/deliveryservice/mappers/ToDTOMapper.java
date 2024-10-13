package ru.ershov.project.deliveryservice.mappers;

import org.mapstruct.Context;
import ru.ershov.project.deliveryservice.util.DistanceCalculator;

public interface ToDTOMapper<E, D> {

    D toDTO(E entity);
    D toDTO(E entity, String courierCoordinates, @Context DistanceCalculator distanceService);


}
