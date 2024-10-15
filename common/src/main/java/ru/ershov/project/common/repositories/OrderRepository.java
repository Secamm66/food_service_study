package ru.ershov.project.common.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ershov.project.common.models.Order;
import ru.ershov.project.common.models.statuses.OrderStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId, Pageable pageable); // Or

    Optional<Order> findByIdAndCustomerId(Long id, Long customer_id); // Or

    List<Order> findAllByRestaurantIdAndStatusIn(Long restaurantId, List<OrderStatus> statuses, Pageable pageable); // Ki

    List<Order> findAllByStatus(OrderStatus status, Pageable pageable); // De

    List<Order> findAllByStatusAndCourierId(OrderStatus status, Long courierId, Pageable pageable); // De

}
