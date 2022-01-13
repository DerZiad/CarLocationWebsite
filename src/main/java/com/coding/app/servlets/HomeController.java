package com.coding.app.servlets;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	private final static String PAGE_ADMIN="index-a";
	
	private final static String PAGE_MANAGER="manager/index-a";
	
	@GetMapping("/admin")
	public ModelAndView getManagerPage() {
		ModelAndView model = new ModelAndView(PAGE_ADMIN);
		return model;
	}
	
	@GetMapping("/manager")
	public ModelAndView getAdminPage() {
		ModelAndView model = new ModelAndView(PAGE_MANAGER);
		model.addObject("username",SecurityContextHolder.getContext().getAuthentication().getName());
		return model;
	}
	
}
