package ru.ershov.project.orderservice.models.statuses;

public enum OrderStatus {

    ACCEPTED, // Заказ принят

    PREPARING, // Готовится

    READY, // Готово

    ON_THE_WAY,// В пути

    DELIVERED, // Доставлено

    CANCELLED, // Отменено

    AWAITING_PAYMENT, // Ожидает оплаты

    PAID, // Оплачено

    AWAITING_CONFIRMATION, // Ожидает подтверждения

    AWAITING_ASSEMBLY // Ожидает сборки

}
