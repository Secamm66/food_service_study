package ru.ershov.project.kitchenservice.dto.restaurantMenuItem;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MenuItemsPostResponse {
    String message;
}
