//package ru.ershov.project.orderservice.services;
//
//import org.springframework.stereotype.Service;
//import ru.ershov.project.orderservice.dto.ItemDTO;
//import ru.ershov.project.orderservice.dto.OrderDTO;
//import ru.ershov.project.orderservice.dto.RestaurantDTO;
//import ru.ershov.project.orderservice.models.Order;
//import ru.ershov.project.orderservice.models.OrderItem;
//import ru.ershov.project.orderservice.models.Restaurant;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class ConvertService {
//
//    public OrderDTO convertToOrderDTO(Order order) {
//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setId(order.getId());
//        orderDTO.setRestaurant(convertToRestaurantDTO(order.getRestaurant()));
//        orderDTO.setOrderDate(order.getOrderDate());
//        orderDTO.setItems(convertToItemDTOList(order.getOrderItems()));
//        return orderDTO;
//    }
//
//    private RestaurantDTO convertToRestaurantDTO(Restaurant restaurant) {
//        RestaurantDTO restaurantDTO = new RestaurantDTO();
//        restaurantDTO.setName(restaurant.getName());
//        ;
//        return restaurantDTO;
//    }
//
//    private List<ItemDTO> convertToItemDTOList(List<OrderItem> orderItems) {
//        List<ItemDTO> itemDTOList = orderItems.stream()
//                .map(item -> {
//                    ItemDTO itemDTO = new ItemDTO();
//                    itemDTO.setCost(item.getPrice() * item.getQuantity());
//                    itemDTO.setQuantity(item.getQuantity());
//                    itemDTO.setImagePath(item.getRestaurantMenuItem().getImagePath());
//                    itemDTO.setDescription(item.getRestaurantMenuItem().getDescription());
//                    return itemDTO;
//                }).collect(Collectors.toList());
//        return itemDTOList;
//    }
//
//}
