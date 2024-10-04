package ru.ershov.project.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDTO {

    private long id;

    private RestaurantResponseDTO restaurant;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime orderDate;

    private List<ItemResponseDTO> items;

}
