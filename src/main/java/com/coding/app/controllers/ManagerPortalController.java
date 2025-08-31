package com.coding.app.controllers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.coding.app.controllers.ManagerPortalAttributes.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ManagerPortalAttributes {

    public static final String MANAGER_PORTAL_URI = "/manager";
    public static final String ATTR_USERNAME = "username";
    public static final String PAGE_MANAGER = "manager/index-a";
}

@Controller
public class ManagerPortalController {

    @GetMapping(MANAGER_PORTAL_URI)
    public ModelAndView getAdminPage() {
        final ModelAndView model = new ModelAndView(PAGE_MANAGER);
        model.addObject(ATTR_USERNAME, SecurityContextHolder.getContext().getAuthentication().getName());
        return model;
    }
}
