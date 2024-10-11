package ru.ershov.project.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ershov.project.orderservice.models.RestaurantMenuItem;

import java.util.Optional;

@Repository

public interface RestaurantMenuItemRepository extends JpaRepository<RestaurantMenuItem, Long> {

    Optional<RestaurantMenuItem> findById(Long id);

}
