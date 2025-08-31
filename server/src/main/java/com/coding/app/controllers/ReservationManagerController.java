package com.coding.app.controllers;

import com.coding.app.exceptions.NotFoundException;
import com.coding.app.models.Reservation;
import com.coding.app.models.User;
import com.coding.app.models.Voiture;
import com.coding.app.models.key.KeyReservation;
import com.coding.app.repository.ReservationRepository;
import com.coding.app.repository.UserRepository;
import com.coding.app.repository.VoitureRepository;
import com.coding.app.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/manager/reservation")
public class ReservationManagerController {

	@Autowired
	private VoitureRepository voitureRepository;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private EmailService emailService;

	private final static String PAGE_RESERVATION = "manager/reservation";
	private final static String ATTRIBUT_RESERVATIONS = "reservations";
	private final static String REDIRECT_RESERVATION = "redirect:/manager/reservation";

	@GetMapping
	public ModelAndView getVoiture() {
		ModelAndView model = new ModelAndView(PAGE_RESERVATION);
		List<Reservation> reservations = reservationRepository.findAll();
		reservations = reservations.stream().filter((r) -> !r.isConfirmed()).toList();
		model.addObject(ATTRIBUT_RESERVATIONS, reservations);
		return model;
	}

	@GetMapping("/accepter/{idVoiture}/{idUser}")
	public ModelAndView accepterReservation(@PathVariable("idVoiture") Long idVoiture,
			@PathVariable("idUser") String username) throws NotFoundException {

		Voiture voiture = voitureRepository.findById(idVoiture)
				.orElseThrow(() -> new NotFoundException("Voiture not found"));
		User user = userRepository.findById(username).orElseThrow(() -> new NotFoundException("User not found"));

		Reservation reservation = new Reservation();
		reservation.setConfirmed(true);
		reservation.setUser(user);
		reservation.setVoiture(voiture);
		reservation.setId(new KeyReservation(user.getUsername(), voiture.getId()));
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

		Voiture voiture = voitureRepository.findById(idVoiture)
				.orElseThrow(() -> new NotFoundException("Voiture not found"));
		User user = userRepository.findById(idUser).orElseThrow(() -> new NotFoundException("User not found"));

		Reservation reservation = new Reservation();
		reservation.setConfirmed(true);
		reservation.setUser(user);
		reservation.setVoiture(voiture);
		reservation.setId(new KeyReservation(user.getUsername(), voiture.getId()));
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
