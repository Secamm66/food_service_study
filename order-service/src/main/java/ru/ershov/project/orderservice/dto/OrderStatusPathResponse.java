package ru.ershov.project.orderservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class OrderStatusPathResponse {

    private Long orderId;
    private String orderStatus;

}
