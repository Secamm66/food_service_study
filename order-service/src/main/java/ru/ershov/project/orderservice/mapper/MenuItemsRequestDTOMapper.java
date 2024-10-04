package ru.ershov.project.orderservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ershov.project.orderservice.dto.MenuItemsRequestDTO;
import ru.ershov.project.orderservice.models.OrderItem;

@Mapper(componentModel = "spring")
public interface MenuItemsRequestDTOMapper extends ToEntityMapper<OrderItem, MenuItemsRequestDTO>{

    @Mapping(source = "menuItemId", target = "restaurantMenuItem.id")
    @Override
    OrderItem toEntity(MenuItemsRequestDTO dto);
}
