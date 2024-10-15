package ru.ershov.project.deliveryservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ershov.project.deliveryservice.dto.MessageResponse;
import ru.ershov.project.deliveryservice.models.Courier;
import ru.ershov.project.deliveryservice.repositories.CourierRepository;
import ru.ershov.project.deliveryservice.util.CourierParametersValidator;
import ru.ershov.project.deliveryservice.util.EntityNotFoundException;
import ru.ershov.project.deliveryservice.util.InvalidPageParameterException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourierService {
    private final CourierRepository courierRepository;

    @Transactional
    public MessageResponse updateCourierStatus(Long courierId, String courierStatus) {
        if (courierId == null || courierStatus == null) {
            throw new InvalidPageParameterException("courierId or courierStatus not found");
        }
        Courier courier = courierRepository.findById(courierId).orElseThrow(()
                -> new EntityNotFoundException("Courier with id " + courierId + " not found"));
        CourierParametersValidator.checkCourierStatusParametersBeforeUpdate(courier, courierStatus);
        CourierParametersValidator.convertToCourierStatusParametersForUpdate(courier, courierStatus);
        courierRepository.save(courier);
        return new MessageResponse().setMessage("The status has been successfully changed to " + courier.getStatus());
    }
}
