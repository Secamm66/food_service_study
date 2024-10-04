package ru.ershov.project.orderservice.mapper;

import org.mapstruct.Mapper;
import ru.ershov.project.orderservice.dto.RestaurantResponseDTO;
import ru.ershov.project.orderservice.models.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantResponseDTOMapper extends ToDTOMapper<Restaurant, RestaurantResponseDTO> {

    @Override
    RestaurantResponseDTO toDTO(Restaurant entity);
}
