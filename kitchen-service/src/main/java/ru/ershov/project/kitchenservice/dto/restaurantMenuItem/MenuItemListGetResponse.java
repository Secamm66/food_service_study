package ru.ershov.project.kitchenservice.dto.restaurantMenuItem;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class MenuItemListGetResponse {

    private List<MenuItemDTO> orders;
    private int pageIndex;
    private int pageCount;

}
