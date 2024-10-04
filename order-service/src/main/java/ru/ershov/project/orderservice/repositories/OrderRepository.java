package ru.ershov.project.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ershov.project.orderservice.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
