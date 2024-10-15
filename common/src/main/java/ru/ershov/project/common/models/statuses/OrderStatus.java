package ru.ershov.project.common.models.statuses;

public enum OrderStatus {

    ACCEPTED, // Заказ принят

    PAID, // Оплачен

    ACTIVE, // Подтвержден, готовится

    DENIED, // Отменён

    COMPLETE, // Готов

    ACCEPTED_FOR_DELIVERY, // Принят в доставку

    DELIVERED // Доставлен

}
