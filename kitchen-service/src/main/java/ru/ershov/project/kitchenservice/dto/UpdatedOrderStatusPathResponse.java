package ru.ershov.project.kitchenservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class UpdatedOrderStatusPathResponse {

    private Long orderId;
    private String message;

}
