package ru.ershov.project.orderservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ershov.project.orderservice.dto.*;
import ru.ershov.project.orderservice.mapper.OrderRequestDTOMapper;
import ru.ershov.project.orderservice.mapper.OrderResponseDTOMapper;
import ru.ershov.project.orderservice.models.*;
import ru.ershov.project.orderservice.models.statuses.OrderStatus;
import ru.ershov.project.orderservice.models.statuses.RestaurantStatus;
import ru.ershov.project.orderservice.repositories.OrderRepository;
import ru.ershov.project.orderservice.repositories.RestaurantMenuItemRepository;
import ru.ershov.project.orderservice.repositories.RestaurantRepository;
import ru.ershov.project.orderservice.util.EntityNotFoundException;
import ru.ershov.project.orderservice.util.InvalidPageParameterException;
import ru.ershov.project.orderservice.util.RestaurantIsNotOpenException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantMenuItemRepository restaurantMenuItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderResponseDTOMapper orderResponseDTOMapper;
    private final OrderRequestDTOMapper orderRequestDTOMapper;
    private final Long customerId = 1L; // TODO: Будет назначаться из сессии


    public OrderListGetResponse getAllOrdersToSend(int pageIndex, int pageCount) {
        checkPaginationParameters(pageIndex, pageCount);
        List<OrderDTOGetResponse> orderDTOGetResponseList = getOrdersByCustomerId(customerId, PageRequest.of(pageIndex, pageCount)).stream()
                .map(orderResponseDTOMapper::toDTO)
                .collect(Collectors.toList());
        OrderListGetResponse orderListGetResponse = new OrderListGetResponse();
        orderListGetResponse.setOrders(orderDTOGetResponseList).setPageIndex(pageIndex).setPageCount(pageCount);
        return orderListGetResponse;
    }

    public OrderDTOGetResponse getOrderById(Long id) {
        return orderResponseDTOMapper.toDTO(orderRepository.findByIdAndCustomerId(id, customerId)
                .orElseThrow(() -> new EntityNotFoundException("Order with id=" + id + " not found")));
    }

    private List<Order> getOrdersByCustomerId(Long customerId, Pageable pageable) {
        return orderRepository.findByCustomerId(customerId, pageable);
    }

    private void checkPaginationParameters(int pageIndex, int pageCount) {
        if (pageIndex < 0 || pageCount < 1) {
            throw new InvalidPageParameterException("Invalid page parameters");
        }
    }

    @Transactional
    public OrderCreatePostResponse createOrder(OrderRequestDTO dto) {
        Restaurant restaurant = getRestaurantAndCheckMenuItemsById(dto);
        Order order = orderRequestDTOMapper.toEntity(dto);
        enrichOrder(order, restaurant);
        orderRepository.save(order);
        OrderCreatePostResponse orderCreatePostResponse = new OrderCreatePostResponse();
        orderCreatePostResponse.setId(order.getId()).setSecretPaymentUrl("Ссылка для оплаты").setEstimatedTimeOfArrival("5 минут");

        return orderCreatePostResponse;
    }

    @Transactional
    public OrderStatusPathResponse updateOrderStatus(Long id) {
        Order orderToUpdate = getOrderToUpdate(id);
        orderToUpdate.setStatus(OrderStatus.PAID);
        OrderStatusPathResponse orderStatusPathResponse = new OrderStatusPathResponse();
        return orderStatusPathResponse.setOrderId(id).setOrderStatus("PAID");
    }

    private Restaurant getRestaurantAndCheckMenuItemsById(OrderRequestDTO dto) {
        Restaurant restaurant = getRestaurantById(dto.getRestaurantId());

        if (restaurant.getStatus() == RestaurantStatus.CLOSED) {
            throw new RestaurantIsNotOpenException("Restaurant " + restaurant.getName() + " is closed now");
        }
        for (MenuItemsRequestDTO menuItem : dto.getMenuItems()) {
            RestaurantMenuItem restaurantMenuItem = getRestaurantMenuItemById(menuItem.getMenuItemId());

            if (!restaurantMenuItem.getRestaurant().getId().equals(restaurant.getId())) {
                throw new IllegalArgumentException("Menu item does not belong to the selected restaurant");
            }
        }
        return restaurant;
    }

    private void enrichOrder(Order order, Restaurant restaurant) {
        Customer customer = new Customer();
        customer.setId(customerId);
        order.setCustomer(customer).setStatus(OrderStatus.ACCEPTED).setOrderDate(LocalDateTime.now());

        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
            restaurant.getRestaurantMenuItems().stream()
                    .filter(menuItem -> menuItem.getId().equals(orderItem.getRestaurantMenuItem().getId()))
                    .findFirst()
                    .ifPresent(menuItem -> orderItem.setPrice(menuItem.getPrice() * orderItem.getQuantity()));
        }
    }

    private Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(()
                        -> new EntityNotFoundException("Restaurant with id=" + id + " not found"));
    }

    private RestaurantMenuItem getRestaurantMenuItemById(Long id) {
        return restaurantMenuItemRepository.findById(id)
                .orElseThrow(()
                        -> new EntityNotFoundException("Restaurant with id=" + id + " not found"));
    }

    private Order getOrderToUpdate(Long id) {
        return orderRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Order with id=" + id + " not found"));
    }

}
