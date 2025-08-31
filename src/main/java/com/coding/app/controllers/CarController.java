package com.coding.app.controllers;

import com.coding.app.exceptions.InvalidObjectException;
import com.coding.app.exceptions.NotFoundException;
import com.coding.app.models.Car;
import com.coding.app.models.enums.Brand;
import com.coding.app.models.enums.Category;
import com.coding.app.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shared/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    private static final String PAGE_CAR = "dashboard-portal/page_car";
    private static final String REDIRECT_CAR = "redirect:/shared/car";

    private static final String ATTR_BRANDS = "brands";
    private static final String ATTR_CATEGORIES = "categories";
    private static final String ATTR_YEARS = "years";
    private static final String ATTR_ERRORS = "errors";
    private static final String ATTR_CAR = "car";
    private static final String ATTR_CARS = "cars";

    @GetMapping
    public ModelAndView showCarPage() {
        ModelAndView model = new ModelAndView(PAGE_CAR);
        model.addObject(ATTR_CATEGORIES, Category.values());
        model.addObject(ATTR_BRANDS, Brand.values());
        model.addObject(ATTR_YEARS, getYearList());
        model.addObject(ATTR_CARS, carService.getAllCars());
        return model;
    }

    @PostMapping
    public ModelAndView createCar(@RequestPayload Car car) {
        ModelAndView model = new ModelAndView(PAGE_CAR);
        try {
            carService.addCar(car);
            model = new ModelAndView(REDIRECT_CAR);
        } catch (InvalidObjectException e) {
            model.addObject(ATTR_CATEGORIES, Category.values());
            model.addObject(ATTR_BRANDS, Brand.values());
            model.addObject(ATTR_YEARS, getYearList());
            model.addObject(ATTR_ERRORS, e.getErrors());
            model.addObject(ATTR_CAR, car);
        }
        return model;
    }

    @GetMapping("/{carId}")
    public ModelAndView showCarById(@PathVariable("carId") Long carId) throws NotFoundException {
        final Car car = carService.getCar(carId);
        ModelAndView model = new ModelAndView(PAGE_CAR);
        model.addObject("modification", true);
        model.addObject(ATTR_CAR, car);
        model.addObject(ATTR_CATEGORIES, Category.values());
        model.addObject(ATTR_BRANDS, Brand.values());
        model.addObject(ATTR_YEARS, getYearList());
        model.addObject(ATTR_CARS, carService.getAllCars());
        return model;
    }

    @PostMapping("/{carId}")
    public ModelAndView updateCarById(@RequestPayload Car car) {
        ModelAndView model = new ModelAndView(PAGE_CAR);
        try {
            carService.updateCar(car);
            model = new ModelAndView(REDIRECT_CAR);
        } catch (InvalidObjectException e) {
            model.addObject(ATTR_CATEGORIES, Category.values());
            model.addObject(ATTR_BRANDS, Brand.values());
            model.addObject(ATTR_YEARS, getYearList());
            model.addObject(ATTR_ERRORS, e.getErrors());
            model.addObject(ATTR_CAR, car);
        }
        return model;
    }

    @GetMapping("/delete/{carId}")
    public ModelAndView deleteCar(@PathVariable("carId") Long carId) throws NotFoundException {
        carService.deleteCar(carId);
        return new ModelAndView(REDIRECT_CAR);
    }

    private static List<Integer> getYearList() {
        List<Integer> years = new ArrayList<>();
        int currentYear = Year.now().getValue();
        for (int i = 1999; i <= currentYear; i++) {
            years.add(i);
        }
        return years;
    }
}
