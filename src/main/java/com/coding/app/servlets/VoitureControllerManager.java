package com.coding.app.servlets;

import java.util.HashMap;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import com.coding.app.exceptions.NotFoundException;
import com.coding.app.models.Voiture;
import com.coding.app.models.enums.Categorie;
import com.coding.app.models.enums.MarqueVoiture;
import com.coding.app.repository.VoitureRepository;

@Controller
@RequestMapping("/manager/voiture")
public class VoitureControllerManager {
	@Autowired
	private VoitureRepository voitureRepository;

	private final static String PAGE_VOITURE = "manager/voiture";
	private final static String REDIRECT_VOITURE = "redirect:/manager/voiture";

	private final static String ATTRIBUT_MARQUES = "marques";
	private final static String ATTRIBUT_CATEGORIES = "categories";
	private final static String ATTRIBUT_ANNEES = "annees";
	private final static String ATTRIBUT_ERRORS = "errors";
	private final static String ATTRIBUT_VOITURE = "voiture";
	private final static String ATTRIBUT_VOITURES = "voitures";

	@GetMapping
	public ModelAndView getVoiture() {
		ModelAndView model = new ModelAndView(PAGE_VOITURE);
		model.addObject(ATTRIBUT_CATEGORIES, Categorie.values());
		model.addObject(ATTRIBUT_MARQUES, MarqueVoiture.values());
		model.addObject(ATTRIBUT_ANNEES, Voiture.getAnnees());
		model.addObject(ATTRIBUT_VOITURES,voitureRepository.findAll());
		model.addObject("username",SecurityContextHolder.getContext().getAuthentication().getName());
		return model;
	}

	@PostMapping
	public ModelAndView saveVoiture(@RequestPayload Voiture voiture) {
		ModelAndView model = new ModelAndView(PAGE_VOITURE);

		HashMap<String, String> errors = new HashMap<String, String>();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Voiture>> violatons = validator.validate(voiture);
		for (ConstraintViolation<Voiture> constraintViolation : violatons) {
			errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
		}
		if (errors.size() == 0) {
			voitureRepository.save(voiture);
			model = new ModelAndView(REDIRECT_VOITURE);
		} else {
			model.addObject(ATTRIBUT_CATEGORIES, Categorie.values());
			model.addObject(ATTRIBUT_MARQUES, MarqueVoiture.values());
			model.addObject(ATTRIBUT_ANNEES, Voiture.getAnnees());
			model.addObject(ATTRIBUT_ERRORS, errors);
			model.addObject(ATTRIBUT_VOITURE,voiture);
			model.addObject("username",SecurityContextHolder.getContext().getAuthentication().getName());
		}
		
		return model;
	}
	
	@GetMapping("/{idVoiture}")
	public ModelAndView getVoitureByID(@PathVariable("idVoiture")Long idVoiture) throws NotFoundException{
		Voiture voiture = voitureRepository.findById(idVoiture).orElseThrow(()-> new NotFoundException("La voiture n'est pas trouvé"));
		ModelAndView model = new ModelAndView(PAGE_VOITURE);
		model.addObject("modification",true);
		model.addObject(ATTRIBUT_VOITURE,voiture);
		model.addObject(ATTRIBUT_CATEGORIES, Categorie.values());
		model.addObject(ATTRIBUT_MARQUES, MarqueVoiture.values());
		model.addObject(ATTRIBUT_ANNEES, Voiture.getAnnees());
		model.addObject(ATTRIBUT_VOITURES,voitureRepository.findAll());
		model.addObject("username",SecurityContextHolder.getContext().getAuthentication().getName());
		return model;
	}

	@PostMapping("/{idVoiture}")
	public ModelAndView saveVoitureByID(@RequestPayload Voiture voiture) {
		ModelAndView model = new ModelAndView(PAGE_VOITURE);

		HashMap<String, String> errors = new HashMap<String, String>();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Voiture>> violatons = validator.validate(voiture);
		for (ConstraintViolation<Voiture> constraintViolation : violatons) {
			errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
		}

		if (errors.size() == 0) {
			voitureRepository.save(voiture);
			model = new ModelAndView(REDIRECT_VOITURE);
		} else {
			model.addObject(ATTRIBUT_CATEGORIES, Categorie.values());
			model.addObject(ATTRIBUT_MARQUES, MarqueVoiture.values());
			model.addObject(ATTRIBUT_ANNEES, Voiture.getAnnees());
			model.addObject(ATTRIBUT_ERRORS, errors);
			model.addObject(ATTRIBUT_VOITURE,voiture);
			model.addObject("username",SecurityContextHolder.getContext().getAuthentication().getName());
		}
		return model;
	}
	
	@GetMapping("/delete/{idVoiture}")
	public ModelAndView deleteVoiture(@PathVariable("idVoiture")Long idVoiture) throws NotFoundException{
		voitureRepository.deleteById(idVoiture);
		ModelAndView model = new ModelAndView(REDIRECT_VOITURE);
		return model;
	}

}
