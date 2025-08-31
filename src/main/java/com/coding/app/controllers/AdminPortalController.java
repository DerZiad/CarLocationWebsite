package com.coding.app.controllers;

import com.coding.app.exceptions.InvalidObjectException;
import com.coding.app.exceptions.NotFoundException;
import com.coding.app.models.User;
import com.coding.app.models.enums.ServerRole;
import com.coding.app.services.HistoryService;
import com.coding.app.services.UserService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.util.List;

import static com.coding.app.controllers.AdminViewAttributes.ADMIN_URI;
import static com.coding.app.controllers.AdminViewAttributes.JSP_ADMIN_DASHBOARD;
import static com.coding.app.controllers.ClientViewAttributes.*;
import static com.coding.app.controllers.HistoryViewAttributes.ADMIN_HISTORY_URI;
import static com.coding.app.controllers.ManagerViewAttributes.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ClientViewAttributes {

    public final static String ADMIN_CLIENT_URI = "/admin/client";
    public final static String JSP_ADMIN_CLIENT = "admin-portal/client";
    public final static String MODEL_AND_VIEW_CLIENTS = "clients";
    public final static String REDIRECT_ADMIN_CLIENT = "redirect:" + ADMIN_CLIENT_URI;
}

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ManagerViewAttributes {

    public final static String ADMIN_MANAGER_URI = "/admin/manager";
    public final static String JSP_ADMIN_MANAGER = "admin-portal/manager";
    public final static String MODEL_AND_VIEW_MANAGERS_ATTRIBUTE = "managers";
    public final static String MODEL_AND_VIEW_MANAGER_ATTRIBUTE = "manager";
    public final static String REDIRECT_MANAGER = "redirect:" + ADMIN_MANAGER_URI;
}

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class AdminViewAttributes {

    public final static String ADMIN_URI = "/admin";
    public final static String JSP_ADMIN_DASHBOARD = "admin-portal/index";
}

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class HistoryViewAttributes {

    public final static String ADMIN_HISTORY_URI = "/admin/history";
    public final static String JSP_ADMIN_HISTORY = "admin-portal/ListHistorique";
    public final static String MODEL_AND_VIEW_HISTORIES_ATTRIBUTE = "historiques";
}

@Controller
@RequiredArgsConstructor
public class AdminPortalController {

    private final UserService userService;
    private final HistoryService historyService;

    @GetMapping(ADMIN_URI)
    public ModelAndView getAdminDashboard() {
        return new ModelAndView(JSP_ADMIN_DASHBOARD);
    }

    @GetMapping(ADMIN_HISTORY_URI)
    public ModelAndView getHistories() {
        final ModelAndView model = new ModelAndView("ListHistorique");
        model.addObject("historiques", historyService.getAllHistories());
        return model;
    }

    @GetMapping(ADMIN_HISTORY_URI + "/clear")
    public ModelAndView clearHistory() {
        historyService.clearHistory();
        return new ModelAndView("redirect:/admin/historiques");
    }

    @GetMapping(ADMIN_MANAGER_URI)
    public ModelAndView getManagers() {
        ModelAndView model = new ModelAndView(JSP_ADMIN_MANAGER);
        final List<User> users = userService.findUsersByFilter(user -> user.getRoles().contains(ServerRole.MANAGER.getRole()));
        model.addObject(MODEL_AND_VIEW_MANAGERS_ATTRIBUTE,users);
        return model;
    }

    @PostMapping(ADMIN_MANAGER_URI)
    public ModelAndView saveManager(@RequestPayload User user) {
        ModelAndView model;
        try {
            user = userService.createManager(user);
            model = new ModelAndView(REDIRECT_MANAGER);
        }catch (InvalidObjectException e) {
            model = new ModelAndView(JSP_ADMIN_MANAGER);
            final List<User> users = userService.findUsersByFilter(u -> u.getRoles().contains(ServerRole.MANAGER.getRole()));
            model.addObject(MODEL_AND_VIEW_MANAGERS_ATTRIBUTE,users);
            model.addObject(MODEL_AND_VIEW_MANAGER_ATTRIBUTE,user);
            model.addObject("errors",e.getErrors());
        }
        return model;
    }

    @GetMapping(ADMIN_CLIENT_URI)
    public ModelAndView getClients() {
        final ModelAndView model = new ModelAndView(JSP_ADMIN_CLIENT);
        final List<User> users = userService.findUsersByFilter(user -> user.getRoles().contains(ServerRole.CLIENT.getRole()));
        model.addObject(MODEL_AND_VIEW_CLIENTS,users);
        return model;
    }

    @GetMapping("/ban/{username}")
    public ModelAndView banUser(@PathVariable("username") String username) throws NotFoundException {
        userService.banUser(username);
        return new ModelAndView(REDIRECT_ADMIN_CLIENT);
    }
}
