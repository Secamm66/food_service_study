package ru.ershov.project.kitchenservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ershov.project.kitchenservice.dto.OrderDTOGetResponse;
import ru.ershov.project.kitchenservice.dto.OrderListGetResponse;
import ru.ershov.project.kitchenservice.dto.UpdatedOrderStatusPathResponse;
import ru.ershov.project.kitchenservice.mappers.OrderDTOGetResponseMapper;
import ru.ershov.project.kitchenservice.models.Order;
import ru.ershov.project.kitchenservice.models.statuses.OrderStatus;
import ru.ershov.project.kitchenservice.repositories.OrderRepository;
import ru.ershov.project.kitchenservice.util.EntityNotFoundException;
import ru.ershov.project.kitchenservice.util.OrderParametersValidator;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDTOGetResponseMapper orderDTOGetResponseMapper;
    private final Long restaurantId = 1L; // TODO: Будет назначаться из сессии

    public OrderListGetResponse getAllOrdersToSend(List<String> orderStatuses, int pageIndex, int pageCount) {
        OrderParametersValidator.checkPaginationParameters(pageIndex, pageCount);
        List<OrderStatus> statusList = OrderParametersValidator.checkAndConvertOrderStatusParametersForFiltration(orderStatuses);
        List<OrderDTOGetResponse> orderList = getOrdersByRestaurantId(restaurantId, statusList, PageRequest.of(pageIndex, pageCount)).stream()
                .map(orderDTOGetResponseMapper::toDTO)
                .collect(Collectors.toList());
        OrderListGetResponse orderListGetResponse = new OrderListGetResponse();
        return orderListGetResponse.setOrders(orderList).setPageIndex(pageIndex).setPageCount(pageCount);
    }

    @Transactional
    public UpdatedOrderStatusPathResponse processOrder(Long orderId, String orderStatusToUpdate) {

        Order orderToUpdate = getOrderById(orderId);
        OrderParametersValidator.checkOrderStatusParametersBeforeUpdate(orderToUpdate, orderStatusToUpdate);
        OrderParametersValidator.convertToOrderStatusParametersForUpdate(orderToUpdate, orderStatusToUpdate);
        orderRepository.save(orderToUpdate);
        UpdatedOrderStatusPathResponse updatedOrderStatusPathResponse = new UpdatedOrderStatusPathResponse();
        updatedOrderStatusPathResponse.setOrderId(orderId).setMessage("Order status from id=" + orderId + " changed to " + orderStatusToUpdate);
        return updatedOrderStatusPathResponse;
    }


    private List<Order> getOrdersByRestaurantId(Long restaurantId, List<OrderStatus> orderStatuses, Pageable pageable) {
        return orderRepository.findAllByRestaurantIdAndStatusIn(restaurantId, orderStatuses);
    }

    private Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(()
                -> new EntityNotFoundException("Order with id=" + orderId + " not found"));
    }

}
