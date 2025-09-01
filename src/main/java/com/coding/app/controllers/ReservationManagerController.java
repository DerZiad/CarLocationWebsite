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

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/manager/reservation")
@RequiredArgsConstructor
public class ReservationManagerController {

	private final CarRepository carRepository;

	private final UserRepository userRepository;

	private final ReservationRepository reservationRepository;

	private final EmailService emailService;

	private static final String PAGE_RESERVATION = "manager/reservation";
	private static final String ATTRIBUT_RESERVATIONS = "reservations";
	private static final String REDIRECT_RESERVATION = "redirect:/manager/reservation";

	@GetMapping("/accepter/{idVoiture}/{idUser}")
	public ModelAndView acceptReservation(@PathVariable("idVoiture") Long idVoiture,
			@PathVariable("idUser") String username) throws NotFoundException {

		Car car = carRepository.findById(idVoiture)
				.orElseThrow(() -> new NotFoundException("Voiture not found"));
		User user = userRepository.findById(username).orElseThrow(() -> new NotFoundException("User not found"));

		Reservation reservation = new Reservation();
		reservation.setConfirmed(true);
		reservation.setUser(user);
		reservation.setCar(car);
		reservation.setId(new KeyReservation(user.getUsername(), car.getId()));
		reservationRepository.save(reservation);

		/*Thread emailSend = new Thread(new Runnable() {

			@Override
			public void run() {
				HtmlMessage htmlMessage = new EmailConfirmation(
						user.getEmail(), "Ta reservation de " + voiture.getMarque().toString() + " "
								+ voiture.getCategorie().toString() + " a été accepter",
						"Reservation accepté", user.getUsername());
				try {
					emailService.sendEmail(htmlMessage);
				} catch (MessagingException e) {
					e.printStackTrace();
				}

			}
		});
		emailSend.start();*/
		return new ModelAndView(REDIRECT_RESERVATION);
	}

	@GetMapping("/delete/{idVoiture}/{idUser}")
	public ModelAndView deleteReservation(@PathVariable("idVoiture") Long idVoiture,
			@PathVariable("idUser") String idUser) throws NotFoundException {

		Car car = carRepository.findById(idVoiture)
				.orElseThrow(() -> new NotFoundException("Voiture not found"));
		User user = userRepository.findById(idUser).orElseThrow(() -> new NotFoundException("User not found"));

		Reservation reservation = new Reservation();
		reservation.setConfirmed(true);
		reservation.setUser(user);
		reservation.setCar(car);
		reservation.setId(new KeyReservation(user.getUsername(), car.getId()));
		reservationRepository.delete(reservation);
		/*Thread emailSend = new Thread(new Runnable() {

			@Override
			public void run() {
				HtmlMessage htmlMessage = new EmailConfirmation(
						user.getEmail(), "Ta reservation de " + voiture.getMarque().toString() + " "
								+ voiture.getCategorie().toString() + " a été refusé",
						"Reservation refusé", user.getUsername());
				try {
					emailService.sendEmail(htmlMessage);
				} catch (MessagingException e) {
					e.printStackTrace();
				}

			}
		});
		emailSend.start();*/
		return new ModelAndView(REDIRECT_RESERVATION);
	}
}
