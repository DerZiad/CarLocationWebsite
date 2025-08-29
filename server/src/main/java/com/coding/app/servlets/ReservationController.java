package com.coding.app.servlets;

import com.coding.app.models.Reservation;
import com.coding.app.repository.ReservationRepository;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/reservation")
public class ReservationController {
	
	private final static String PAGE_RESERVATION = "admin/reservation";
	private final static String ATTRIBUT_RESERVATIONS = "reservations";
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@GetMapping
	@RolesAllowed("ADMIN")
	public ModelAndView listeReservation() {
		ModelAndView model = new ModelAndView(PAGE_RESERVATION);
		List<Reservation> reservations = reservationRepository.findAll();
		reservations = reservations.stream().filter((r)->r.isConfirmed()).toList();
		model.addObject(ATTRIBUT_RESERVATIONS, reservations);
		return model;
	}
}
