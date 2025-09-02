package com.coding.app.services;

import java.util.HashMap;
import java.util.List;

import com.coding.app.dto.CarRequest;
import org.springframework.stereotype.Service;

import com.coding.app.exceptions.InvalidObjectException;
import com.coding.app.exceptions.NotFoundException;
import com.coding.app.models.Car;
import com.coding.app.repository.CarRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final HistoryService historyService;

    public void addCar(final CarRequest carRequest, final MultipartFile imageFile) throws InvalidObjectException {
        final Car car = new Car();
        car.setBrand(carRequest.getBrand());
        car.setCategory(carRequest.getCategory());
        car.setYear(carRequest.getYear());
        car.setPrice(carRequest.getPrice());
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                car.setImage(imageFile.getBytes());
            } catch (final Exception ignored) {

            }
        }
        final HashMap<String, String> errors = Utils.validate(car);
        if (errors.isEmpty()) {
            historyService.addHistory("New car added: " + car.getBrand());
            carRepository.save(car);
        } else {
            throw new InvalidObjectException("Invalid Car object", errors);
        }
    }

    public void deleteCar(final Long carId) throws NotFoundException {
        final Car car = carRepository.findById(carId).orElseThrow(() -> new NotFoundException("Car not found"));
        carRepository.delete(car);
        historyService.addHistory("Car deleted: " + car.getBrand());
    }

    public Car getCar(final Long carId) throws NotFoundException {
        return carRepository.findById(carId).orElseThrow(() -> new NotFoundException("Car not found"));
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public void updateCar(final Car car) throws InvalidObjectException {
        final HashMap<String, String> errors = Utils.validate(car);
        if (errors.isEmpty()) {
            historyService.addHistory("Car updated: " + car.getBrand());
            carRepository.save(car);
        } else {
            throw new InvalidObjectException("Invalid Car object", errors);
        }
    }
}
