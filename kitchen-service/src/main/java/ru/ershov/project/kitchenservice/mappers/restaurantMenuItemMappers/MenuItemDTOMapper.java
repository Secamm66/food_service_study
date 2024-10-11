package ru.ershov.project.kitchenservice.mappers.restaurantMenuItemMappers;

import org.mapstruct.Mapper;
import ru.ershov.project.kitchenservice.dto.restaurantMenuItem.MenuItemDTO;
import ru.ershov.project.kitchenservice.mappers.ToDTOMapper;
import ru.ershov.project.kitchenservice.models.RestaurantMenuItem;

@Mapper(componentModel = "spring")
public interface MenuItemDTOMapper extends ToDTOMapper<RestaurantMenuItem, MenuItemDTO> {

}
