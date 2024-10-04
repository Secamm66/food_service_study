package ru.ershov.project.orderservice.dto;

import lombok.Data;

import java.util.List;
@Data
public class OrderGetResponse {

    private List<OrderResponseDTO> orders;
    private int pageIndex;
    private int pageCount;

}
