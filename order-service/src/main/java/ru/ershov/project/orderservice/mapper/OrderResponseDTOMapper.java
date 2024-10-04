package ru.ershov.project.orderservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ershov.project.orderservice.dto.OrderResponseDTO;
import ru.ershov.project.orderservice.models.Order;

@Mapper(componentModel = "spring", uses = {ItemResponseDTOMapper.class})
public interface OrderResponseDTOMapper extends ToDTOMapper<Order, OrderResponseDTO> {

    @Mapping(source = "orderItems", target = "items")
    @Override
    OrderResponseDTO toDTO(Order entity);
}
