package ru.ershov.project.deliveryservice.mappers.ToDTODeliveriesMappers;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.ershov.project.deliveryservice.dto.responseToGetDeliveries.RestaurantDTO;
import ru.ershov.project.deliveryservice.mappers.ToDTOMapper;
import ru.ershov.project.deliveryservice.models.Restaurant;
import ru.ershov.project.deliveryservice.util.DistanceCalculator;

@Mapper(componentModel = "spring", uses = DistanceCalculator.class)

public interface RestaurantDTOMapper extends ToDTOMapper<Restaurant, RestaurantDTO> {

    @Override
    @Mapping(target = "distance", expression = "java(distanceService.calculateDistance(courierCoordinates, entity.getCoordinates()))")
    RestaurantDTO toDTO(Restaurant entity, String courierCoordinates, @Context DistanceCalculator distanceService);

    @Override
    RestaurantDTO toDTO(Restaurant entity);
//    @Named("calculateDistance")
//    default Double calculateDistance(String entityCoordinates, String courierCoordinates, DistanceCalculator dc) {
//        return dc.calculateDistance(courierCoordinates, entityCoordinates);
//  }
}
