package ru.ershov.project.deliveryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ershov.project.deliveryservice.models.Order;
import ru.ershov.project.deliveryservice.models.statuses.OrderStatus;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByRestaurantIdAndStatusIn(Long restaurantId, List<OrderStatus> statuses);

}
