package ru.ershov.project.orderservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class OrderCreatePostResponse {

    private Long id;
    private String secretPaymentUrl;
    private String estimatedTimeOfArrival;

}
