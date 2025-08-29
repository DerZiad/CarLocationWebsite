package com.coding.app.servlets;

import com.coding.app.exceptions.NotFoundException;
import com.coding.app.models.User;
import com.coding.app.models.enums.ServerRole;
import com.coding.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/client")
public class ClientServlet {
	private final static String PATH_MANAGER = "admin/client";
	private final static String ATTRIBUT_MANAGERS = "clients";
	
	private final static String REDIRECT_MANAGERS = "redirect:/admin/client";
	
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public ModelAndView getManagers() {
		ModelAndView model = new ModelAndView(PATH_MANAGER);
		List<User> users = userRepository.findAll();
		users = users.stream().filter((u)->u.getRoles().indexOf(ServerRole.CLIENT.getRole()) != -1).toList();
		model.addObject(ATTRIBUT_MANAGERS,users);
		return model;
	}
		
	@GetMapping("/ban/{idUser}")
	public ModelAndView getManagers(@PathVariable("idUser")Long idUser) throws NotFoundException {
		User user = userRepository.findById(idUser).orElseThrow(()-> new NotFoundException("User introuvable"));
		user.setEnabled(false);
		userRepository.save(user);
		ModelAndView model = new ModelAndView(REDIRECT_MANAGERS);
		return model;
	}
}
