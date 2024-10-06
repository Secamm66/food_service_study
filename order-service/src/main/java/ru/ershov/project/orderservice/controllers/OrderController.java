package ru.ershov.project.orderservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ershov.project.orderservice.dto.OrderPostResponse;
import ru.ershov.project.orderservice.dto.OrderRequestDTO;
import ru.ershov.project.orderservice.dto.OrderResponseDTO;
import ru.ershov.project.orderservice.dto.OrderGetResponse;
import ru.ershov.project.orderservice.services.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<OrderGetResponse> getAllOrders(@RequestParam(name = "page_index", defaultValue = "0") int pageIndex,
                                                         @RequestParam(name = "page_count", defaultValue = "10") int pageCount) {
        OrderGetResponse orderGetResponse = orderService.getAllOrdersToSend(pageIndex, pageCount);
        return ResponseEntity.ok(orderGetResponse);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable("id") int id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping("/order")
    public ResponseEntity<OrderPostResponse> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        OrderPostResponse orderPostResponse = orderService.createOrder(orderRequestDTO);
        return ResponseEntity.ok(orderPostResponse);
    }

}
