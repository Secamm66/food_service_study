//package ru.ershov.project.kitchenservice.services;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.validation.BindingResult;
//import ru.ershov.project.kitchenservice.dto.restaurantMenuItem.MenuItemDTO;
//import ru.ershov.project.kitchenservice.dto.restaurantMenuItem.MenuItemListGetResponse;
//import ru.ershov.project.kitchenservice.dto.restaurantMenuItem.MenuItemRequestDTO;
//import ru.ershov.project.kitchenservice.dto.restaurantMenuItem.MenuItemsPostResponse;
//import ru.ershov.project.kitchenservice.mappers.restaurantMenuItemMappers.MenuItemDTOMapper;
//import ru.ershov.project.kitchenservice.mappers.restaurantMenuItemMappers.MenuItemRequestDTOMapper;
//import ru.ershov.project.kitchenservice.models.Restaurant;
//import ru.ershov.project.kitchenservice.models.RestaurantMenuItem;
//import ru.ershov.project.kitchenservice.repositories.RestaurantMenuItemRepository;
//import ru.ershov.project.kitchenservice.util.*;
//
//import javax.persistence.EntityManager;
//import javax.persistence.FlushModeType;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//public class RestaurantMenuItemService {
//
//    @PersistenceContext
//    private final EntityManager entityManager;
//    private final RestaurantMenuItemRepository restaurantMenuItemRepository;
//    private final MenuItemDTOMapper menuItemDTOMapper;
//    private final MenuItemRequestDTOMapper menuItemRequestDTOMapper;
//    private final RestaurantMenuItemValidator restaurantMenuItemValidator;
//    public static final Long restaurantId = 1L; // TODO: Будет назначаться из сессии
//
//    public MenuItemListGetResponse getAllMenuItemsToSend(int pageIndex, int pageCount) {
//        OrderParametersValidator.checkPaginationParameters(pageIndex, pageCount);
//        List<MenuItemDTO> dtoList = getMenuItemsByRestaurantId(restaurantId, PageRequest.of(pageIndex, pageCount)).stream()
//                .map(menuItemDTOMapper::toDTO)
//                .collect(Collectors.toList());
//        MenuItemListGetResponse response = new MenuItemListGetResponse();
//        return response.setOrders(dtoList).setPageIndex(pageIndex).setPageCount(pageCount);
//    }
//
//    public MenuItemDTO getMenuItemByIdToSend(Long id) {
//        RestaurantMenuItem menuItem = getRestaurantMenuItemById(id, restaurantId);
//        return menuItemDTOMapper.toDTO(menuItem);
//    }
//
//    @Transactional
//    public MenuItemsPostResponse createMenuItem(MenuItemRequestDTO dto, BindingResult bindingResult) {
//
//        RestaurantMenuItem menuItem = menuItemRequestDTOMapper.toEntity(dto);
//        restaurantMenuItemValidator.validate(menuItem, bindingResult);
//        enrichMenuItem(menuItem);
//        restaurantMenuItemRepository.save(menuItem);
//        return new MenuItemsPostResponse().setMessage("Successfully created menu item with id=" + menuItem.getId());
//    }
//
//    @Transactional
//    public MenuItemsPostResponse updateMenuItem(Long id, MenuItemRequestDTO dto, BindingResult bindingResult) {
//        entityManager.setFlushMode(FlushModeType.COMMIT);
//        RestaurantMenuItem menuItemToUpdate = getRestaurantMenuItemById(id, restaurantId);
//        menuItemToUpdate.setName(dto.getName()).setPrice(dto.getPrice()).setImagePath(dto.getImagePath()).setDescription(dto.getDescription());
//        restaurantMenuItemValidator.validate(menuItemToUpdate, bindingResult);
//        restaurantMenuItemRepository.save(menuItemToUpdate);
//        return new MenuItemsPostResponse().setMessage("Successfully updated menu item with id=" + id);
//    }
//
//    @Transactional
//    public MenuItemsPostResponse deleteMenuItem(Long id) {
//        int count = restaurantMenuItemRepository.deleteByIdAndRestaurantIdAndReturnCount(id, restaurantId);
//        if (count == 0) {
//            throw new EntityNotFoundException("Menu item with id=" + id + " not found");
//        }
//        return new MenuItemsPostResponse().setMessage("Successfully delete menu item with id=" + id);
//    }
//
//    private void enrichMenuItem(RestaurantMenuItem menuItem) {
//        Restaurant restaurant = new Restaurant();
//        restaurant.setId(restaurantId);
//        menuItem.setRestaurant(restaurant);
//    }
//
//    private List<RestaurantMenuItem> getMenuItemsByRestaurantId(Long restaurantId, Pageable pageable) {
//        return restaurantMenuItemRepository.findAllByRestaurantId(restaurantId);
//    }
//
//    private RestaurantMenuItem getRestaurantMenuItemById(Long id, Long restaurantId) {
//        return restaurantMenuItemRepository.findByIdAndRestaurantId(id, restaurantId)
//                .orElseThrow(() -> new EntityNotFoundException("Restaurant menu item with id=" + id + " not found"));
//    }
//
//}
