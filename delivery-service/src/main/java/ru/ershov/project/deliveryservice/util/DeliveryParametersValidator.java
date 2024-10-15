package ru.ershov.project.deliveryservice.util;

public class DeliveryParametersValidator {

    public static void checkPaginationParameters(int pageIndex, int pageCount) {
        if (pageIndex < 0 || pageCount < 1) {
            throw new InvalidPageParameterException("Invalid pagination parameters");
        }
    }
}
