package ru.ershov.project.deliveryservice.dto.responseToGetDeliveries;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class DeliveryListGetResponse {

    private List<DeliveryResponseDTO> delivery;
    private int pageIndex;
    private int pageCount;

}