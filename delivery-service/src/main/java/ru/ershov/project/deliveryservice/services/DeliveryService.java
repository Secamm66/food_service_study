package ru.ershov.project.deliveryservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ershov.project.deliveryservice.dto.MessageResponse;
import ru.ershov.project.deliveryservice.dto.OrderActionRequestDTO;
import ru.ershov.project.deliveryservice.dto.responseToGetDeliveries.DeliveryListGetResponse;
import ru.ershov.project.deliveryservice.dto.responseToGetDeliveries.DeliveryResponseDTO;
import ru.ershov.project.deliveryservice.mappers.ToDTODeliveriesMappers.DeliveryResponseDTOMapper;
import ru.ershov.project.deliveryservice.models.Courier;
import ru.ershov.project.deliveryservice.models.Order;
import ru.ershov.project.deliveryservice.models.statuses.CourierStatus;
import ru.ershov.project.deliveryservice.models.statuses.OrderStatus;
import ru.ershov.project.deliveryservice.repositories.CourierRepository;
import ru.ershov.project.deliveryservice.repositories.OrderRepository;
import ru.ershov.project.deliveryservice.util.DeliveryParametersValidator;
import ru.ershov.project.deliveryservice.util.EntityNotFoundException;
import ru.ershov.project.deliveryservice.util.InvalidPageParameterException;
import ru.ershov.project.deliveryservice.util.PaymentCalculator;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryService {

    private final OrderRepository orderRepository;
    private final CourierRepository courierRepository;
    private final DeliveryResponseDTOMapper mapper;

    private final Long courierId = 1L; // TODO: Будет назначаться из сессии
    private final String courierCoordinates = "56.3075,43.9858"; // TODO: Будут браться со стороннего GPS-сервиса

    public DeliveryListGetResponse getAllDeliveriesToSend(String deliveryStatus, int pageIndex, int pageCount) {
        DeliveryParametersValidator.checkPaginationParameters(pageIndex, pageCount);
        List<Order> orderList = getAllOrdersByStatus(deliveryStatus, PageRequest.of(pageIndex, pageCount));
        List<DeliveryResponseDTO> deliveryList = orderList.stream()
                .map(order -> mapper.toDTO(order, courierCoordinates))
                .collect(Collectors.toList());
        PaymentCalculator.calculateDeliveryCost(deliveryList);
        DeliveryListGetResponse deliveryListGetResponse = new DeliveryListGetResponse();
        return deliveryListGetResponse.setDelivery(deliveryList).setPageIndex(pageIndex).setPageCount(pageCount);
    }

    @Transactional
    public MessageResponse processDelivery(OrderActionRequestDTO dto, Long id) {
        Order orderToUpdateStatus = getOrderById(id);
        if (orderToUpdateStatus.getStatus().equals(OrderStatus.COMPLETE)) {
            if (dto.getOrderAction().equalsIgnoreCase("accept")) {
                Courier courier = getCourierById(courierId);
                courier.setStatus(CourierStatus.BUSY);
                orderToUpdateStatus.setStatus(OrderStatus.ACCEPTED_FOR_DELIVERY).setCourier(courier);
                orderRepository.save(orderToUpdateStatus);
                return new MessageResponse().setMessage("You have accepted this order for delivery!");
            }
        }
        if (orderToUpdateStatus.getStatus().equals(OrderStatus.ACCEPTED_FOR_DELIVERY)) {
            if (dto.getOrderAction().equalsIgnoreCase("complete")) {
                Courier courier = getCourierById(courierId);
                courier.setStatus(CourierStatus.AVAILABLE);
                orderToUpdateStatus.setStatus(OrderStatus.DELIVERED).setCourier(courier);
                orderRepository.save(orderToUpdateStatus);
                return new MessageResponse().setMessage("You delivered this order!");
            }
        }
        throw new InvalidPageParameterException("Invalid order action or order status");
    }

    private List<Order> getAllOrdersByStatus(String deliveryStatus, Pageable pageable) {
        List<Order> orderList;
        if (deliveryStatus.equalsIgnoreCase("active")) {
            orderList = orderRepository.findAllByStatus(OrderStatus.COMPLETE, pageable);
        } else if (deliveryStatus.equalsIgnoreCase("complete")) {
            orderList = orderRepository.findAllByStatusAndCourierId(OrderStatus.DELIVERED, courierId, pageable);
        } else {
            throw new InvalidPageParameterException("Invalid status parameters");
        }
        return orderList;
    }

    private Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Order with id=" + id + " not found"));
    }

    private Courier getCourierById(Long id) {
        return courierRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Courier with id=" + id + " not found"));
    }

}
