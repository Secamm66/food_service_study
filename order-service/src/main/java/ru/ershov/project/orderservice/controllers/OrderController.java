package ru.ershov.project.orderservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ershov.project.orderservice.dto.*;
import ru.ershov.project.orderservice.services.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<OrderListGetResponse> getAllOrders(@RequestParam(name = "page_index", defaultValue = "0") int pageIndex,
                                                             @RequestParam(name = "page_count", defaultValue = "10") int pageCount) {
        OrderListGetResponse orderListGetResponse = orderService.getAllOrdersToSend(pageIndex, pageCount);
        return ResponseEntity.ok(orderListGetResponse);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTOGetResponse> getOrder(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping("/order")
    public ResponseEntity<OrderCreatePostResponse> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        OrderCreatePostResponse orderCreatePostResponse = orderService.createOrder(orderRequestDTO);
        return ResponseEntity.ok(orderCreatePostResponse);
    }

    @PatchMapping("/order/{id}/update_status_to_payed")
    public ResponseEntity<OrderStatusPathResponse> updateOrderStatus(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id));
    }

}
