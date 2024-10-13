//package ru.ershov.project.kitchenservice.controllers;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ru.ershov.project.kitchenservice.dto.UpdatedRestaurantStatusPathResponse;
//import ru.ershov.project.kitchenservice.services.RestaurantService;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/restaurant")
//public class RestaurantController {
//
//    private final RestaurantService restaurantService;
//
//    @PatchMapping()
//    public ResponseEntity<UpdatedRestaurantStatusPathResponse> updateRestaurantStatus
//            (@RequestParam(name = "status") String statusToUpdate) {
//        UpdatedRestaurantStatusPathResponse response = restaurantService.updateRestaurantStatus(statusToUpdate);
//        return ResponseEntity.ok(response);
//    }
//
//}
