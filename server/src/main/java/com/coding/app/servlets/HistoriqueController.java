package com.coding.app.servlets;

import com.coding.app.repository.HistoriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/historiques")
public class HistoriqueController {
	
	
	@Autowired
	private HistoriqueRepository historiqueRepository;
	
	@GetMapping
	public ModelAndView getHistoriques() {
		ModelAndView model = new ModelAndView("ListHistorique");
		model.addObject("historiques",historiqueRepository.findAll());
		return model;
	}
	
	@GetMapping("/vider")
	public ModelAndView viderHistorique() {
		historiqueRepository.deleteAll();
		return new ModelAndView("redirect:/admin/historiques");
	}
}
