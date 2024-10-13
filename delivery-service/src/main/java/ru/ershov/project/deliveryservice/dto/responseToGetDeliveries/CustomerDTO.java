package ru.ershov.project.deliveryservice.dto.responseToGetDeliveries;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomerDTO {
    String address;
    Double distance;
}
