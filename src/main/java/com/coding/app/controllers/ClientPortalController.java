package com.coding.app.controllers;

import com.coding.app.models.Voiture;
import com.coding.app.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ClientPortalController {
	
	private final static String PATH_CLIENT = "client/index";
	
	private final static String ATTRIBUT_VOITURES = "voitures";
	
	@Autowired
	private VoitureRepository voitureRepository;
	
	@GetMapping
	public ModelAndView getString() {
		ModelAndView model = new ModelAndView(PATH_CLIENT);		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth instanceof AnonymousAuthenticationToken)) {
			model.addObject("username",auth.getName());
		}
		
		List<Voiture> voitures = voitureRepository.findAll();
		if(voitures.size() < 6) {
			model.addObject(ATTRIBUT_VOITURES,voitures);
		}else {
			List<Voiture> sortedVoitures = new ArrayList<Voiture>();
			for (int i = 0; i < 6; i++) {
				sortedVoitures.add(voitures.get(i));
			}
			model.addObject(ATTRIBUT_VOITURES,sortedVoitures);
		}
		
		return model;
	}
	
}
