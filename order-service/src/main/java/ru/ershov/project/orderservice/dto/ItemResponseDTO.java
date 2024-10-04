package ru.ershov.project.orderservice.dto;

import lombok.Data;

@Data
public class ItemResponseDTO {

    private String name;
    private int cost;
    private int quantity;
    private String description;
    private String imagePath;

}