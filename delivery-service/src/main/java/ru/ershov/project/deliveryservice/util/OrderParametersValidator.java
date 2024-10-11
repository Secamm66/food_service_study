package ru.ershov.project.deliveryservice.util;

import ru.ershov.project.deliveryservice.models.Order;
import ru.ershov.project.deliveryservice.models.statuses.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class OrderParametersValidator {

    public static void checkPaginationParameters(int pageIndex, int pageCount) {
        if (pageIndex < 0 || pageCount < 1) {
            throw new InvalidPageParameterException("Invalid pagination parameters");
        }
    }

    public static List<OrderStatus> checkAndConvertOrderStatusParametersForFiltration(List<String> orderStatuses) {
        List<OrderStatus> orderStatusesList = new ArrayList<>();
        for (String orderStatus : orderStatuses) {
            if (orderStatus.equalsIgnoreCase("active")) {
                orderStatusesList.add(OrderStatus.ACTIVE);
            }
            if (orderStatus.equalsIgnoreCase("complete")) {
                orderStatusesList.add(OrderStatus.COMPLETE);
                orderStatusesList.add(OrderStatus.ACCEPTED_FOR_DELIVERY);
                orderStatusesList.add(OrderStatus.DELIVERED);
            }
            if (orderStatus.equalsIgnoreCase("denied")) {
                orderStatusesList.add(OrderStatus.DENIED);
            }
            if (!orderStatus.equalsIgnoreCase("active") && !orderStatus.equalsIgnoreCase("complete") && !orderStatus.equalsIgnoreCase("denied")) {
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
