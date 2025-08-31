package com.coding.app.controllers;

import com.coding.app.exceptions.InvalidObjectException;
import com.coding.app.exceptions.NotFoundException;
import com.coding.app.models.Reservation;
import com.coding.app.models.User;
import com.coding.app.models.enums.ServerRole;
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

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ClientViewAttributes {

    public final static String ADMIN_CLIENT_URI = "/admin/client";
    public final static String JSP_ADMIN_CLIENT = "dashboard-portal/page_client";
    public final static String MODEL_AND_VIEW_CLIENTS = "clients";
    public final static String REDIRECT_ADMIN_CLIENT = "redirect:" + ADMIN_CLIENT_URI;
}

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ManagerViewAttributes {

    public final static String ADMIN_MANAGER_URI = "/admin/manager";
    public final static String JSP_ADMIN_MANAGER = "dashboard-portal/page_manager";
    public final static String MODEL_AND_VIEW_MANAGERS_ATTRIBUTE = "managers";
    public final static String MODEL_AND_VIEW_MANAGER_ATTRIBUTE = "manager";
    public final static String REDIRECT_MANAGER = "redirect:" + ADMIN_MANAGER_URI;
}

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class HistoryViewAttributes {

    public final static String ADMIN_HISTORY_URI = "/admin/history";
    public final static String JSP_ADMIN_HISTORY = "dashboard-portal/page_history";
    public final static String MODEL_AND_VIEW_HISTORIES_ATTRIBUTE = "historyData";
    public final static String REDIRECT_HISTORY = "redirect:" + ADMIN_MANAGER_URI;
}

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final UserService userService;
    private final HistoryService historyService;
    private final ReservationService reservationService;

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

    @GetMapping(ADMIN_HISTORY_URI)
    public ModelAndView getHistoryPage() {
        final ModelAndView model = new ModelAndView(JSP_ADMIN_HISTORY);
        configureCurrentUser(model);
        model.addObject(MODEL_AND_VIEW_HISTORIES_ATTRIBUTE, historyService.getAllHistories());
        return model;
    }

    @GetMapping(ADMIN_HISTORY_URI + "/clear")
    public ModelAndView clearHistory() {
        historyService.clearHistory();
        return new ModelAndView(REDIRECT_HISTORY);
    }

    @GetMapping(ADMIN_MANAGER_URI)
    public ModelAndView getManagersPage() {
        final ModelAndView model = new ModelAndView(JSP_ADMIN_MANAGER);
        configureCurrentUser(model);
        final List<User> users = userService.findUsersByFilter(user -> user.getRoles().contains(ServerRole.MANAGER.getRole()));
        model.addObject(MODEL_AND_VIEW_MANAGERS_ATTRIBUTE, users);
        return model;
    }

    @PostMapping(ADMIN_MANAGER_URI)
    public ModelAndView addManager(@RequestPayload User user) {
        ModelAndView model;
        try {
            user = userService.createManager(user);
            model = new ModelAndView(REDIRECT_MANAGER);
        } catch (InvalidObjectException e) {
            model = new ModelAndView(JSP_ADMIN_MANAGER);
            configureCurrentUser(model);
            final List<User> users = userService.findUsersByFilter(u -> u.getRoles().contains(ServerRole.MANAGER.getRole()));
            model.addObject(MODEL_AND_VIEW_MANAGERS_ATTRIBUTE, users);
            model.addObject(MODEL_AND_VIEW_MANAGER_ATTRIBUTE, user);
            model.addObject("errors", e.getErrors());
        }
        return model;
    }

    @GetMapping(ADMIN_CLIENT_URI)
    public ModelAndView getClientsPage() {
        final ModelAndView model = new ModelAndView(JSP_ADMIN_CLIENT);
        configureCurrentUser(model);
        final List<User> users = userService.findUsersByFilter(user -> user.getRoles().contains(ServerRole.CLIENT.getRole()));
        model.addObject(MODEL_AND_VIEW_CLIENTS, users);
        return model;
    }

    @GetMapping("/admin/ban")
    public ModelAndView banUser(@RequestParam("userType") String userType, @RequestParam("username") String username) throws NotFoundException {
        userService.banUser(username);
        if(userType.equals("manager")) {
            return new ModelAndView(REDIRECT_MANAGER);
        } else {
            return new ModelAndView(REDIRECT_ADMIN_CLIENT);
        }
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
