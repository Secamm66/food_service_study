package ru.ershov.project.orderservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {

    private long restaurantId;
    private List<MenuItemsRequestDTO> menuItems;

}