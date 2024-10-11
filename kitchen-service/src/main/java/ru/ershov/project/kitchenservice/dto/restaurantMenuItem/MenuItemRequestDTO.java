package ru.ershov.project.kitchenservice.dto.restaurantMenuItem;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Data
@Accessors(chain = true)
public class MenuItemRequestDTO {

    @NotEmpty(message = "Укажите название блюда")
    @Size(min = 5, max = 100, message = "Название блюда может содержать от 5 до 100 символов")
    private String name;

    @NotNull(message = "Укажите стоимость блюда")
    @Min(value = 10, message = "Стоимость не может быть меньше 10 рублей")
    private Integer price;

    @Size(max = 255, message = "Длина пути не должна превышать 255 символов")
    private String imagePath;

    @Size(max = 255, message = "Описание блюда не может превышать 255 символов")
    private String description;

}
