package ru.ershov.project.orderservice.models.statuses;

public enum OrderStatus {

    ACCEPTED, // Заказ принят

    PAID, // Оплачен

    ACTIVE, // Подтвержден, готовится

    DENIED, // Отменён

    COMPLETE, // Готов

    AWAITING_ASSEMBLY, // Ожидает сборки

    ON_THE_WAY, // В пути

    DELIVERED // Доставлен

}
