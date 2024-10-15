package ru.ershov.project.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ershov.project.common.models.Courier;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {

}
