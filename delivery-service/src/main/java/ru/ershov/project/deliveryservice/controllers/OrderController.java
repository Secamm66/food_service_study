//package ru.ershov.project.kitchenservice.controllers;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ru.ershov.project.kitchenservice.dto.DeliveryListGetResponse;
//import ru.ershov.project.kitchenservice.dto.UpdatedOrderStatusPathResponse;
//import ru.ershov.project.kitchenservice.services.OrderService;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/orders")
//public class OrderController {
//
//    private final OrderService orderService;
//
//    @GetMapping()
//    public ResponseEntity<DeliveryListGetResponse> getAllOrders(@RequestParam(name = "status", defaultValue = "active") List<String> orderStatuses,
//                                                                @RequestParam(name = "page_index", defaultValue = "0") int pageIndex,
//                                                                @RequestParam(name = "page_count", defaultValue = "10") int pageCount) {
//        DeliveryListGetResponse orderResponse = orderService.getAllOrdersToSend(orderStatuses, pageIndex, pageCount);
//        return ResponseEntity.ok(orderResponse);
//    }
//
//    @PatchMapping("/process_order/{id}")
//    public ResponseEntity<UpdatedOrderStatusPathResponse> processOrder(@PathVariable("id") Long orderId,
//                                                                       @RequestParam(name = "status") String orderStatusToUpdate) {
//        UpdatedOrderStatusPathResponse response = orderService.processOrder(orderId, orderStatusToUpdate);
//        return ResponseEntity.ok(response);
//    }
//
//}
