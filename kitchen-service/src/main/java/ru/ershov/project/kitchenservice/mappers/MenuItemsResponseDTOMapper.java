package ru.ershov.project.kitchenservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ershov.project.kitchenservice.dto.MenuItemsResponseDTO;
import ru.ershov.project.kitchenservice.models.OrderItem;

@Mapper(componentModel = "spring")
public interface MenuItemsResponseDTOMapper extends ToDTOMapper<OrderItem, MenuItemsResponseDTO> {

    @Override
    @Mapping(source = "restaurantMenuItem.id", target = "menuItemId")
    MenuItemsResponseDTO toDTO(OrderItem item);
}