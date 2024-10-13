package ru.ershov.project.deliveryservice.util;

import ru.ershov.project.deliveryservice.models.Order;
import ru.ershov.project.deliveryservice.models.statuses.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class DeliveryParametersValidator {

    public static void checkPaginationParameters(int pageIndex, int pageCount) {
        if (pageIndex < 0 || pageCount < 1) {
            throw new InvalidPageParameterException("Invalid pagination parameters");
        }
    }

    public static List<OrderStatus> checkAndConvertDeliveryStatusParametersForFiltration(List<String> statuses) {
        List<OrderStatus> orderStatusesList = new ArrayList<>();
        for (String deliveryStatus : statuses) {
            if (deliveryStatus.equalsIgnoreCase("active")) {
                orderStatusesList.add(OrderStatus.COMPLETE);
            }
            if (deliveryStatus.equalsIgnoreCase("complete")) {
                orderStatusesList.add(OrderStatus.DELIVERED);
            }
            if (!deliveryStatus.equalsIgnoreCase("active") && !deliveryStatus.equalsIgnoreCase("complete")) {
                throw new InvalidPageParameterException("Invalid status parameters");
            }
        }
        return orderStatusesList;
    }

    public static void checkOrderStatusParametersBeforeUpdate(Order orderToUpdate, String orderStatusToUpdate) {
        OrderStatus currentStatus = orderToUpdate.getStatus();

        if (currentStatus == OrderStatus.PAID) {
            if (orderStatusToUpdate.equalsIgnoreCase("complete")) {
                throw new InvalidPageParameterException("Order with status PAID cannot be set to COMPLETE");
            }
        }
        if (currentStatus == OrderStatus.ACTIVE) {
            if (orderStatusToUpdate.equalsIgnoreCase("active")) {
                throw new InvalidPageParameterException("Order is already ACTIVE");
            }
            if (orderStatusToUpdate.equalsIgnoreCase("denied")) {
                throw new InvalidPageParameterException("Order with status ACTIVE cannot be set to DENIED");
            }
        }
        if (currentStatus == OrderStatus.DENIED || currentStatus == OrderStatus.COMPLETE) {
            throw new InvalidPageParameterException("Order status cannot be changed from DENIED or COMPLETE");
        }
    }

    public static void convertToOrderStatusParametersForUpdate(Order orderToUpdate, String orderStatusToUpdate) {

        if (orderStatusToUpdate.equalsIgnoreCase("active")) {
            orderToUpdate.setStatus(OrderStatus.ACTIVE);
        } else if (orderStatusToUpdate.equalsIgnoreCase("denied")) {
            orderToUpdate.setStatus(OrderStatus.DENIED);
        } else if (orderStatusToUpdate.equalsIgnoreCase("complete")) {
            orderToUpdate.setStatus(OrderStatus.COMPLETE);
        } else {
            throw new InvalidPageParameterException("Invalid status parameters");
        }
    }
}
