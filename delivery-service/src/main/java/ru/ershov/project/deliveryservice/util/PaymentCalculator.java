package ru.ershov.project.deliveryservice.util;

import ru.ershov.project.deliveryservice.dto.responseToGetDeliveries.DeliveryResponseDTO;

import java.util.List;

public class PaymentCalculator {

    public static void calculateDeliveryCost(List<DeliveryResponseDTO> list) {
        list.stream()
                .peek(element -> {
                    double distanceDifference = element.getCustomer().getDistance() - element.getRestaurant().getDistance();
                    int payment = (int) (Math.abs(distanceDifference)+1)*50;
                    element.setPayment(payment);
                }).forEach(element -> {
                });
    }
}
