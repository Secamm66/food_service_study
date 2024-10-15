package ru.ershov.project.deliveryservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class MessageResponse {
    String message;
}
