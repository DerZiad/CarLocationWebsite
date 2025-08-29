package com.coding.app.servlets;

import com.coding.app.exceptions.NotFoundException;
import com.coding.app.models.History;
import com.coding.app.models.User;
import com.coding.app.models.enums.ServerRole;
import com.coding.app.repository.HistoriqueRepository;
import com.coding.app.repository.UserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin/manager")
public class ManagerController {
	
	private final static String PATH_MANAGER = "admin/manager";
	private final static String ATTRIBUT_MANAGERS = "managers";
	
	private final static String REDIRECT_MANAGERS = "redirect:/admin/manager";
	private final static String ATTRIBUT_MANAGER = "manager";
	
	
	@Autowired
	private HistoriqueRepository historiqueRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public ModelAndView getManagers() {
		ModelAndView model = new ModelAndView(PATH_MANAGER);
		List<User> users = userRepository.findAll();
		users = users.stream().filter((u)->u.getRoles().indexOf(ServerRole.MANAGER.getRole()) != -1).toList();
		model.addObject(ATTRIBUT_MANAGERS,users);
		return model;
	}
	
	@PostMapping
	public ModelAndView saveManager(@RequestPayload User user) {
		ModelAndView model = null;
		HashMap<String, String> errors = new HashMap<String, String>();
		user.setPassword("temporalpassword");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<User>> violatons = validator.validate(user);
		for (ConstraintViolation<User> constraintViolation : violatons) {
			errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
		}
		if (errors.size() == 0 && user.getUsername().matches(".*\\..*")) {
			user.setPassword(passwordEncoder.encode(user.getUsername()));
			user.setEnabled(true);
			user.setVerificated(true);
			user.addRole(ServerRole.MANAGER);
			userRepository.save(user);
			model = new ModelAndView(REDIRECT_MANAGERS);
			historiqueRepository.save(new History("New managed added " + user.getUsername()));
		}else {
			System.out.println("Errors");
			model = new ModelAndView(PATH_MANAGER);
			List<User> users = userRepository.findAll();
			users = users.stream().filter((u)->u.getRoles().indexOf(ServerRole.MANAGER.getRole()) != -1).toList();
			model.addObject(ATTRIBUT_MANAGERS,users);
			model.addObject(ATTRIBUT_MANAGER,user);
			model.addObject("errors",errors);
		}

		return model;
	}
	
	@GetMapping("/ban/{idUser}")
	public ModelAndView getManagers(@PathVariable("idUser")Long idUser) throws NotFoundException {
		User user = userRepository.findById(idUser).orElseThrow(()-> new NotFoundException("User introuvable"));
		user.setEnabled(false);
		userRepository.save(user);
		historiqueRepository.save(new History("New managed banned " + user.getUsername()));
		ModelAndView model = new ModelAndView(REDIRECT_MANAGERS);
		return model;
	}
}
