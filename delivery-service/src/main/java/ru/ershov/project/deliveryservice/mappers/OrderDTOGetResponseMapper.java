package ru.ershov.project.deliveryservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ershov.project.kitchenservice.dto.OrderDTOGetResponse;
import ru.ershov.project.kitchenservice.models.Order;

@Mapper(componentModel = "spring", uses = MenuItemsResponseDTOMapper.class)
public interface OrderDTOGetResponseMapper extends ToDTOMapper<Order, OrderDTOGetResponse>{

    @Override
    @Mapping(source = "orderItems", target = "menuItems")
    OrderDTOGetResponse toDTO(Order order);

}
