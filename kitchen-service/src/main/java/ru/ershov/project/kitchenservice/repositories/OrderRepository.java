package ru.ershov.project.kitchenservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ershov.project.kitchenservice.models.Order;
import ru.ershov.project.kitchenservice.models.statuses.OrderStatus;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByRestaurantIdAndStatusIn(Long restaurantId, List<OrderStatus> statuses);

}
