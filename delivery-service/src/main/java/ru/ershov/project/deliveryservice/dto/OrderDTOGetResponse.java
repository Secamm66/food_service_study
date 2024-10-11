package ru.ershov.project.kitchenservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTOGetResponse {

    private Long id;
    private List<MenuItemsResponseDTO> menuItems;

}
