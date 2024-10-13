//package ru.ershov.project.deliveryservice.repositories;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import ru.ershov.project.deliveryservice.dto.restaurantMenuItem.MenuItemDTO;
//import ru.ershov.project.deliveryservice.models.RestaurantMenuItem;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface RestaurantMenuItemRepository extends JpaRepository<RestaurantMenuItem, Long> {
//
//    List<RestaurantMenuItem> findAllByRestaurantId(Long restaurantId);
//
//    Optional<RestaurantMenuItem> findByIdAndRestaurantId(Long itemId, Long restaurantId);
//
//    @Modifying
//    @Transactional
//    @Query("DELETE FROM RestaurantMenuItem rmi WHERE rmi.id = :id and rmi.restaurant.id = :restaurantId")
//    Integer deleteByIdAndRestaurantIdAndReturnCount(@Param("id") Long id, @Param("restaurantId") Long restaurantId);
//
//    @Query("SELECT NEW ru.ershov.project.kitchenservice.dto.restaurantMenuItem.MenuItemDTO(rmi.id, rmi.name) FROM RestaurantMenuItem rmi WHERE rmi.name = :name and rmi.restaurant.id = :restaurantId")
//    Optional<MenuItemDTO> findByNameAndRestaurantId(@Param("name") String name, @Param("restaurantId") Long restaurantId);
//
//}