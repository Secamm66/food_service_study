package ru.ershov.project.kitchenservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@Accessors(chain = true)
public class OrderListGetResponse {

    private List<OrderDTOGetResponse> orders;
    private int pageIndex;
    private int pageCount;

}
