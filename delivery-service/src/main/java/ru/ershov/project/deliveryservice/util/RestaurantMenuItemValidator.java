package ru.ershov.project.deliveryservice.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ershov.project.deliveryservice.dto.restaurantMenuItem.MenuItemDTO;
import ru.ershov.project.deliveryservice.models.RestaurantMenuItem;
import ru.ershov.project.deliveryservice.repositories.RestaurantMenuItemRepository;
import ru.ershov.project.kitchenservice.services.RestaurantMenuItemService;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RestaurantMenuItemValidator implements Validator {

    private final RestaurantMenuItemRepository restaurantMenuItemRepository;

    @Override
    public void validate(Object o, Errors errors) {
        RestaurantMenuItem entity = (RestaurantMenuItem) o;
        Optional<MenuItemDTO> checkingEntity =
                restaurantMenuItemRepository.findByNameAndRestaurantId(entity.getName(), RestaurantMenuItemService.restaurantId);

        if (checkingEntity.isPresent()) {
            if (checkingEntity.get().getId() != entity.getId()) {
                errors.rejectValue("name", "400", "Блюдо с таким именем уже существует");
            }
        }
        if (errors.hasErrors()) {
            ErrorsUtil.returnErrorsToClient((BindingResult) errors);
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RestaurantMenuItem.class.equals(aClass);
    }
}
