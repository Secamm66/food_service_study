package ru.ershov.project.deliveryservice.util;

import ru.ershov.project.deliveryservice.models.Restaurant;
import ru.ershov.project.deliveryservice.models.statuses.RestaurantStatus;

public class RestaurantParametersValidator {

    public static void checkRestaurantStatusParametersBeforeUpdate(Restaurant restaurant, String restaurantStatusToUpdate) {
        if (restaurant.getStatus() == RestaurantStatus.OPEN) {
            if (restaurantStatusToUpdate.equalsIgnoreCase("open")) {
                throw new InvalidPageParameterException("Restaurant is already open");
            }
        }
        if (restaurant.getStatus() == RestaurantStatus.CLOSED) {
            if (restaurantStatusToUpdate.equalsIgnoreCase("closed")) {
                throw new InvalidPageParameterException("Restaurant is already closed");
            }
        }
    }

    public static void convertToOrderStatusParametersForUpdate(Restaurant restaurant, String restaurantStatusToUpdate) {
        if (restaurantStatusToUpdate.equalsIgnoreCase("open")) {
            restaurant.setStatus(RestaurantStatus.OPEN);
        } else if (restaurantStatusToUpdate.equalsIgnoreCase("closed")) {
            restaurant.setStatus(RestaurantStatus.CLOSED);
        } else {
            throw new InvalidPageParameterException("restaurantStatus is not valid");
        }
    }
}
