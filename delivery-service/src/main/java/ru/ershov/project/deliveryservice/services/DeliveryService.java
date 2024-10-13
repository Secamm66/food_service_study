package ru.ershov.project.deliveryservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ershov.project.deliveryservice.dto.responseToGetDeliveries.DeliveryListGetResponse;
import ru.ershov.project.deliveryservice.dto.responseToGetDeliveries.DeliveryResponseDTO;
import ru.ershov.project.deliveryservice.mappers.ToDTODeliveriesMappers.DeliveryResponseDTOMapper;
import ru.ershov.project.deliveryservice.models.Order;
import ru.ershov.project.deliveryservice.models.statuses.OrderStatus;
import ru.ershov.project.deliveryservice.repositories.DeliveryRepository;
import ru.ershov.project.deliveryservice.util.DeliveryParametersValidator;
import ru.ershov.project.deliveryservice.util.DistanceCalculator;
import ru.ershov.project.deliveryservice.util.InvalidPageParameterException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryResponseDTOMapper mapper;
    private final DistanceCalculator distanceCalculator;

    private final Long courierId = 1L; // TODO: Будет назначаться из сессии
    private final String courierCoordinates = "56.3075,43.9858"; // TODO: Будут браться со стороннего GPS-сервиса

    public DeliveryListGetResponse getAllDeliveriesToSend(String deliveryStatus, int pageIndex, int pageCount) {
        DeliveryParametersValidator.checkPaginationParameters(pageIndex, pageCount);

        List<Order> orderList = getAllOrdersByStatus(deliveryStatus, PageRequest.of(pageIndex, pageCount));
        System.out.println(orderList);
        List<DeliveryResponseDTO> deliveryList = orderList.stream()
                .map(order -> mapper.toDTO(order, courierCoordinates, distanceCalculator)).collect(Collectors.toList());
        System.out.println(deliveryList);
//        for (Order order : orderList) {
//            String restaurantCoordinates = order.getRestaurant().getCoordinates();
//            String customerCoordinates = order.getCustomer().getCoordinates();
//
//        }

        DeliveryListGetResponse deliveryListGetResponse = new DeliveryListGetResponse();
        return deliveryListGetResponse.setDelivery(deliveryList).setPageIndex(pageIndex).setPageCount(pageCount);
    }

    private List<Order> getAllOrdersByStatus(String deliveryStatus, Pageable pageable) {
        List<Order> orderList;
        if (deliveryStatus.equalsIgnoreCase("active")) {
            orderList = deliveryRepository.findAllByStatus(OrderStatus.COMPLETE, pageable);
        } else if (deliveryStatus.equalsIgnoreCase("complete")) {
            orderList = deliveryRepository.findAllByStatusAndCourierId(OrderStatus.DELIVERED, courierId, pageable);
        } else {
            throw new InvalidPageParameterException("Invalid status parameters");
        }
        return orderList;
    }
}
