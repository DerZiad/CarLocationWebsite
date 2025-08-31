package com.coding.app.controllers;

import com.coding.app.exceptions.InvalidObjectException;
import com.coding.app.exceptions.NotFoundException;
import com.coding.app.models.Reservation;
import com.coding.app.models.User;
import com.coding.app.models.enums.ServerRole;
import com.coding.app.services.CarService;
import com.coding.app.services.HistoryService;
import com.coding.app.services.ReservationService;
import com.coding.app.services.UserService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.util.List;

import static com.coding.app.controllers.ClientViewAttributes.*;
import static com.coding.app.controllers.DashboardViewAttributes.*;
import static com.coding.app.controllers.HistoryViewAttributes.*;
import static com.coding.app.controllers.ManagerViewAttributes.*;
import static com.coding.app.controllers.ReservationViewAttributes.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DashboardViewAttributes {

    public final static String ADMIN_URI = "/shared";
    public final static String JSP_ADMIN_DASHBOARD = "dashboard-portal/index";
    public final static String CURRENT_USER = "user";
}

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ReservationViewAttributes {

    public final static String ADMIN_RESERVATION_URI = "/shared/reservation";
    public final static String JSP_ADMIN_RESERVATION = "dashboard-portal/page_reservation";
    public final static String MODEL_AND_VIEW_RESERVATIONS_ATTRIBUTE = "reservations";
}

@NoArgsConstructor
class CarViewAttributes {

    public final static String ADMIN_CAR_URI = "/shared/car";
    public final static String JSP_ADMIN_CAR = "dashboard-portal/page_car";
    public final static String MODEL_AND_VIEW_CARS_ATTRIBUTE = "cars";
    public final static String REDIRECT_ADMIN_CAR = "redirect:" + ADMIN_CAR_URI;
}

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final UserService userService;
    private final ReservationService reservationService;
    private final CarService carService;

    @GetMapping(ADMIN_URI)
    public ModelAndView getDashboard() {
        final ModelAndView modelAndView = new ModelAndView(JSP_ADMIN_DASHBOARD);
        configureCurrentUser(modelAndView);
        return modelAndView;
    }

    @GetMapping(ADMIN_RESERVATION_URI)
    public ModelAndView getReservationPage() {
        final ModelAndView model = new ModelAndView(JSP_ADMIN_RESERVATION);
        final List<Reservation> reservations = reservationService.getReservationsByFilter(Reservation::isConfirmed);
        model.addObject(MODEL_AND_VIEW_RESERVATIONS_ATTRIBUTE, reservations);
        return model;
    }

    private void configureCurrentUser(ModelAndView model) {
        try {
            final User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            model.addObject(CURRENT_USER, user);
        } catch (NotFoundException e) {
            model.setViewName("redirect:/");
        }
    }
}
