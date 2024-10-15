package ru.ershov.project.deliveryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ershov.project.deliveryservice.models.Courier;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {

}
