package ru.ershov.project.deliveryservice.dto.responseToGetDeliveries;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DeliveryResponseDTO {

    private Long orderId;
    private RestaurantDTO restaurant;
    private CustomerDTO customer;
    //private Integer payment;

}
