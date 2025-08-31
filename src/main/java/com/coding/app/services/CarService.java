package com.coding.app.services;

import com.coding.app.exceptions.InvalidObjectException;
import com.coding.app.exceptions.NotFoundException;
import com.coding.app.models.Car;
import com.coding.app.models.History;
import com.coding.app.models.enums.Brand;
import com.coding.app.models.enums.Category;
import com.coding.app.repository.CarRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final HistoryService historyService;

    public void addCar(final Car car) throws InvalidObjectException {
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
