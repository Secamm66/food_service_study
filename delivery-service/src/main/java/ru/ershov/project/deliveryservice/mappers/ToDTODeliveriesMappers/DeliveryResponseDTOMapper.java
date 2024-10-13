package ru.ershov.project.deliveryservice.mappers.ToDTODeliveriesMappers;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ershov.project.deliveryservice.dto.responseToGetDeliveries.DeliveryResponseDTO;
import ru.ershov.project.deliveryservice.mappers.ToDTOMapper;
import ru.ershov.project.deliveryservice.models.Order;
import ru.ershov.project.deliveryservice.util.DistanceCalculator;

@Mapper(componentModel = "spring", uses = {CustomerDTOMapper.class, RestaurantDTOMapper.class})
public interface DeliveryResponseDTOMapper extends ToDTOMapper<Order, DeliveryResponseDTO> {

    @Override
    @Mapping(source = "entity.id", target = "orderId")
    DeliveryResponseDTO toDTO(Order entity, String courierCoordinates, @Context DistanceCalculator distanceCalculator);
}

