package ru.ershov.project.orderservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ershov.project.orderservice.dto.*;
import ru.ershov.project.orderservice.mapper.OrderRequestDTOMapper;
import ru.ershov.project.orderservice.mapper.OrderResponseDTOMapper;
import ru.ershov.project.orderservice.models.*;
import ru.ershov.project.orderservice.models.statuses.OrderStatus;
import ru.ershov.project.orderservice.repositories.OrderRepository;
import ru.ershov.project.orderservice.repositories.RestaurantMenuItemRepository;
import ru.ershov.project.orderservice.repositories.RestaurantRepository;
import ru.ershov.project.orderservice.util.EntityNotFoundException;
import ru.ershov.project.orderservice.util.InvalidPageParameterException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantMenuItemRepository restaurantMenuItemRepository;
    public final RestaurantRepository restaurantRepository;
    private final OrderResponseDTOMapper orderResponseDTOMapper;
    private final OrderRequestDTOMapper orderRequestDTOMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        RestaurantMenuItemRepository restaurantMenuItemRepository,
                        RestaurantRepository restaurantRepository,
                        OrderResponseDTOMapper orderResponseDTOMapper,
                        OrderRequestDTOMapper orderRequestDTOMapper) {
        this.orderRepository = orderRepository;
        this.restaurantMenuItemRepository = restaurantMenuItemRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderResponseDTOMapper = orderResponseDTOMapper;
        this.orderRequestDTOMapper = orderRequestDTOMapper;
    }

    public OrderGetResponse getAllOrdersToSend(int pageIndex, int pageCount) {
        OrderGetResponse orderGetResponse = new OrderGetResponse();
        if (pageIndex < 0 || pageCount < 1) {
            throw new InvalidPageParameterException("Invalid page parameters");
        }
        List<OrderResponseDTO> orderResponseDTOList = getAllOrders(PageRequest.of(pageIndex, pageCount)).stream()
                .map(orderResponseDTOMapper::toDTO).collect(Collectors.toList());
        orderGetResponse.setOrders(orderResponseDTOList);
        orderGetResponse.setPageIndex(pageIndex);
        orderGetResponse.setPageCount(pageCount);
        return orderGetResponse;
    }

    public OrderResponseDTO getOrderById(long id) {
        return orderResponseDTOMapper.toDTO(orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with id=" + id + " not found")));
    }

    private List<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll();
    }

    @Transactional
    public OrderPostResponse createOrder(OrderRequestDTO dto) {

        Restaurant restaurant = getRestaurantAndCheckMenuItemsById(dto);

        Order order = orderRequestDTOMapper.toEntity(dto);

        enrichOrder(order, restaurant);

        orderRepository.save(order);

        return new OrderPostResponse(order.getId(), "Ссылка для оплаты", "5 минут");
    }

    private Restaurant getRestaurantAndCheckMenuItemsById(OrderRequestDTO dto) {
        Restaurant restaurant = getRestaurantById(dto.getRestaurantId());

        for (MenuItemsRequestDTO menuItem : dto.getMenuItems()) {
            RestaurantMenuItem restaurantMenuItem = getRestaurantMenuItemById(menuItem.getMenuItemId());

            if (restaurantMenuItem.getRestaurant().getId() != restaurant.getId()) {
                throw new IllegalArgumentException("Menu item does not belong to the selected restaurant");
            }
        }
        return restaurant;
    }

    private void enrichOrder(Order order, Restaurant restaurant) {
        Customer customer = new Customer();
        customer.setId(1); // ???Назначаю пока вручную
        order.setCustomer(customer);
        order.setStatus(OrderStatus.ACCEPTED);
        order.setOrderDate(LocalDateTime.now());

        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
            restaurant.getRestaurantMenuItems().stream()
                    .filter(menuItem -> menuItem.getId() == (orderItem.getRestaurantMenuItem().getId()))
                    .findFirst()
                    .ifPresent(menuItem -> orderItem.setPrice(menuItem.getPrice() * orderItem.getQuantity()));
        }
    }

    private Restaurant getRestaurantById(long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant with id=" + id + " not found"));
    }

    private RestaurantMenuItem getRestaurantMenuItemById(long id) {
        return restaurantMenuItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant with id=" + id + " not found"));
    }
}
