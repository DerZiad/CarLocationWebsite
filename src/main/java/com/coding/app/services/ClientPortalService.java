package com.coding.app.services;

import com.coding.app.dto.CarResponse;
import com.coding.app.exceptions.NotFoundException;
import com.coding.app.models.Reservation;
import com.coding.app.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientPortalService {

    private final CarRepository carRepository;

    public List<CarResponse> getAllCars() {
        return this.carRepository.findAll().stream().map(CarResponse::toCarResponse).toList();
    }

    public CarResponse getCarById(final Long carId) throws NotFoundException {
        return CarResponse.toCarResponse(this.carRepository.findById(carId).orElseThrow(() -> new NotFoundException("Car not found")));
    }

    public List<Reservation> getReservationDuringPeriodForCar(final Long carId, final String startDate, final String endDate) {

        return null; // To be implemented
    }
}
