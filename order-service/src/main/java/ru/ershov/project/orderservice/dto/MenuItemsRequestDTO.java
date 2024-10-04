package ru.ershov.project.orderservice.dto;

import lombok.Data;

@Data
public class MenuItemsRequestDTO {

    private int quantity;
    private long menuItemId;

}
