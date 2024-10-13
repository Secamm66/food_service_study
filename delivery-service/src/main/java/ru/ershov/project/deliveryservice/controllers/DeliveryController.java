package ru.ershov.project.deliveryservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ershov.project.deliveryservice.dto.responseToGetDeliveries.DeliveryListGetResponse;
import ru.ershov.project.deliveryservice.services.DeliveryService;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/deliveries")
    public ResponseEntity<DeliveryListGetResponse> getDeliveries(@RequestParam(name = "status", defaultValue = "active") String deliveryStatus,
                                                                 @RequestParam(name = "page_index", defaultValue = "0") int pageIndex,
                                                                 @RequestParam(name = "page_count", defaultValue = "10") int pageCount) {

        DeliveryListGetResponse response = deliveryService.getAllDeliveriesToSend(deliveryStatus, pageIndex, pageCount);
        return ResponseEntity.ok(response);
    }

//    @PostMapping("/delivery/{id}")
//    public ResponseEntity<HttpStatus> processDelivery(@PathVariable("id") String deliveryId) {
//
//    }

}
