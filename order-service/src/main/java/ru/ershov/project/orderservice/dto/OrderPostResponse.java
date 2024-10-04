package ru.ershov.project.orderservice.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPostResponse {

    private long id;
    private String secretPaymentUrl;
    private String estimatedTimeOfArrival;

}
