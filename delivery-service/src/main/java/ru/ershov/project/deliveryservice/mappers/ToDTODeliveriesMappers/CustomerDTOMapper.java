package ru.ershov.project.deliveryservice.mappers.ToDTODeliveriesMappers;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ershov.project.deliveryservice.dto.responseToGetDeliveries.CustomerDTO;
import ru.ershov.project.deliveryservice.dto.responseToGetDeliveries.RestaurantDTO;
import ru.ershov.project.deliveryservice.mappers.ToDTOMapper;
import ru.ershov.project.deliveryservice.models.Customer;
import ru.ershov.project.deliveryservice.models.Restaurant;
import ru.ershov.project.deliveryservice.util.DistanceCalculator;

@Mapper(componentModel = "spring", uses = DistanceCalculator.class)
public interface CustomerDTOMapper extends ToDTOMapper<Customer, CustomerDTO> {

    @Override
    @Mapping(target = "distance", expression = "java(distanceService.calculateDistance(courierCoordinates, entity.getCoordinates()))")
    CustomerDTO toDTO(Customer entity, String courierCoordinates, @Context DistanceCalculator distanceService);

    @Override
    CustomerDTO toDTO(Customer entity);
}