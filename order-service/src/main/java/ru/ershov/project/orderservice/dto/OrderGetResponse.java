package ru.ershov.project.orderservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@Accessors(chain = true)
public class OrderGetResponse {

    private List<OrderResponseDTO> orders;
    private int pageIndex;
    private int pageCount;

}
