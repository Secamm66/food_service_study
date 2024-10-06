package ru.ershov.project.orderservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class OrderPostResponse {

    private long id;
    private String secretPaymentUrl;
    private String estimatedTimeOfArrival;

}
