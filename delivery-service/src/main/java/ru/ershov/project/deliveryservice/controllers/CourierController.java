package ru.ershov.project.deliveryservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ershov.project.deliveryservice.dto.MessageResponse;
import ru.ershov.project.deliveryservice.services.CourierService;
import ru.ershov.project.deliveryservice.util.InvalidPageParameterException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courier")
public class CourierController {
    private final CourierService courierService;

    @PatchMapping("/{id}")
    public ResponseEntity<MessageResponse> updateCourierStatus(@PathVariable("id") Long courierId,
                                                               @RequestParam(name = "status", required = false)
                                                               String courierStatus) {
        MessageResponse response = courierService.updateCourierStatus(courierId, courierStatus);
        return ResponseEntity.ok(response);
    }
}
