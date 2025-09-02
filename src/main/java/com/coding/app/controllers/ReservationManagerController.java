package com.coding.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.coding.app.exceptions.NotFoundException;
import com.coding.app.models.Car;
import com.coding.app.models.Reservation;
import com.coding.app.models.User;
import com.coding.app.models.key.KeyReservation;
import com.coding.app.repository.CarRepository;
import com.coding.app.repository.ReservationRepository;
import com.coding.app.repository.UserRepository;
import com.coding.app.services.EmailService;
import com.coding.app.services.ReservationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/manager/reservation")
@RequiredArgsConstructor
public class ReservationManagerController {

	private final ReservationService reservationService;

	private static final String REDIRECT_RESERVATION = "redirect:/manager/reservation";

	@GetMapping("/accept/{cardId}/{username}")
	public ModelAndView acceptReservation(@PathVariable("cardId") final Long cardId, @PathVariable("username") final String username) throws NotFoundException {
		reservationService.acceptReservation(cardId, username);
		return new ModelAndView(REDIRECT_RESERVATION);
	}

	@GetMapping("/delete/{cardId}/{username}")
	public ModelAndView deleteReservation(@PathVariable("cardId") final Long cardId, @PathVariable("username") final String username) throws NotFoundException {
		reservationService.deleteReservation(cardId, username);
		return new ModelAndView(REDIRECT_RESERVATION);
	}
}
