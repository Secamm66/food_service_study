//package ru.ershov.project.kitchenservice.services;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ru.ershov.project.kitchenservice.dto.UpdatedRestaurantStatusPathResponse;
//import ru.ershov.project.kitchenservice.models.Restaurant;
//import ru.ershov.project.kitchenservice.repositories.RestaurantRepository;
//import ru.ershov.project.kitchenservice.util.EntityNotFoundException;
//import ru.ershov.project.kitchenservice.util.RestaurantParametersValidator;
//
//@Service
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//public class RestaurantService {
//
//    private final RestaurantRepository restaurantRepository;
//    private final Long restaurantId = 1L; // TODO: Будет назначаться из сессии
//
//    @Transactional
//    public UpdatedRestaurantStatusPathResponse updateRestaurantStatus(String restaurantStatusToUpdate) {
//        Restaurant restaurant = getRestaurantById(restaurantId);
//        RestaurantParametersValidator.checkRestaurantStatusParametersBeforeUpdate(restaurant, restaurantStatusToUpdate);
//        RestaurantParametersValidator.convertToOrderStatusParametersForUpdate(restaurant, restaurantStatusToUpdate);
//        restaurantRepository.save(restaurant);
//        UpdatedRestaurantStatusPathResponse updatedRestaurantStatusPathResponse = new UpdatedRestaurantStatusPathResponse();
//        updatedRestaurantStatusPathResponse.setMessage("Restaurant is " + restaurantStatusToUpdate);
//        return updatedRestaurantStatusPathResponse;
//    }
//
//    private Restaurant getRestaurantById(Long restaurantId) {
//        return restaurantRepository.findById(restaurantId).orElseThrow(()
//                -> new EntityNotFoundException("restaurant with id=" + restaurantId + " not found"));
//    }
//}
