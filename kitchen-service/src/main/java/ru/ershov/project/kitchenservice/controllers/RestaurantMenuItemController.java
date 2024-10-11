package ru.ershov.project.kitchenservice.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ershov.project.kitchenservice.dto.restaurantMenuItem.MenuItemDTO;
import ru.ershov.project.kitchenservice.dto.restaurantMenuItem.MenuItemListGetResponse;
import ru.ershov.project.kitchenservice.dto.restaurantMenuItem.MenuItemRequestDTO;
import ru.ershov.project.kitchenservice.dto.restaurantMenuItem.MenuItemsPostResponse;
import ru.ershov.project.kitchenservice.services.RestaurantMenuItemService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class RestaurantMenuItemController {

    private final RestaurantMenuItemService restaurantMenuItemService;

    @GetMapping
    public ResponseEntity<MenuItemListGetResponse> getAllMenuItems(@RequestParam(name = "page_index", defaultValue = "0") int pageIndex,
                                                                   @RequestParam(name = "page_count", defaultValue = "10") int pageCount) {
        MenuItemListGetResponse response = restaurantMenuItemService.getAllMenuItemsToSend(pageIndex, pageCount);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemDTO> getMenuItem(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(restaurantMenuItemService.getMenuItemByIdToSend(id));
    }

    @PostMapping()
    public ResponseEntity<MenuItemsPostResponse> addMenuItem(@RequestBody @Valid MenuItemRequestDTO dto,
                                                             BindingResult bindingResult) {
        return ResponseEntity.ok(restaurantMenuItemService.createMenuItem(dto, bindingResult));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemsPostResponse> updateMenuItem(@PathVariable(name = "id") Long id,
                                                                @RequestBody @Valid MenuItemRequestDTO dto,
                                                                BindingResult bindingResult) {
        return ResponseEntity.ok(restaurantMenuItemService.updateMenuItem(id, dto, bindingResult));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MenuItemsPostResponse> deleteMenuItem(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(restaurantMenuItemService.deleteMenuItem(id));
    }

}
