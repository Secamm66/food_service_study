package ru.ershov.project.orderservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {

    private Long restaurantId;
    private List<MenuItemsRequestDTO> menuItems;

}