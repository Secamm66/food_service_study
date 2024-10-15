package ru.ershov.project.deliveryservice.util;

import ru.ershov.project.deliveryservice.models.Courier;
import ru.ershov.project.deliveryservice.models.statuses.CourierStatus;

public class CourierParametersValidator {

    public static void checkCourierStatusParametersBeforeUpdate(Courier courier, String courierStatusToUpdate) {
        if (courier.getStatus() == CourierStatus.AVAILABLE) {
            if (courierStatusToUpdate.equalsIgnoreCase("available")) {
                throw new InvalidPageParameterException("You are already free and have started your shift");
            }
        }
        if (courier.getStatus() == CourierStatus.NOT_AVAILABLE) {
            if (courierStatusToUpdate.equalsIgnoreCase("notAvailable")) {
                throw new InvalidPageParameterException("Your shift is already over");
            }
        }
        if (courier.getStatus() == CourierStatus.BUSY) {
            throw new InvalidPageParameterException("You cannot complete a shift or change the status" +
                    "until you have completed all deliveries");
        }
    }

    public static void convertToCourierStatusParametersForUpdate(Courier courier, String courierStatusToUpdate) {
        if (courierStatusToUpdate.equalsIgnoreCase("available")) {
            courier.setStatus(CourierStatus.AVAILABLE);
        } else if (courierStatusToUpdate.equalsIgnoreCase("notAvailable")) {
            courier.setStatus(CourierStatus.NOT_AVAILABLE);
        } else {
            throw new InvalidPageParameterException("restaurantStatus is not valid");
        }
    }
}
