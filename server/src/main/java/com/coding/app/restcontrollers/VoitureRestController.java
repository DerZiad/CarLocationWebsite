package com.coding.app.restcontrollers;

import com.coding.app.dto.CarRequest;
import com.coding.app.exceptions.NotFoundException;
import com.coding.app.models.Reservation;
import com.coding.app.models.User;
import com.coding.app.models.Voiture;
import com.coding.app.models.key.KeyReservation;
import com.coding.app.repository.ReservationRepository;
import com.coding.app.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/api/voitures", produces = MediaType.APPLICATION_JSON_VALUE, value = "/api/voitures")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VoitureRestController {

	@Autowired
	private VoitureRepository voitureRepository;

	@Autowired
	private ReservationRepository reservationRepository;
	
	@GetMapping
	public HttpEntity<?> getCars() {
		return ResponseEntity.ok(voitureRepository.findAll());
	}

	@PostMapping
	public HttpEntity<?> addVoiture(@RequestBody CarRequest voitureRequest) throws NotFoundException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			System.out.println("Traitement");
			Voiture voiture = voitureRepository.findById(voitureRequest.carId())
					.orElseThrow(() -> new NotFoundException("Voiture not found"));
			User user = (User) auth.getPrincipal();
			Reservation reservation = new Reservation();
			System.out.println(voitureRequest.delay());
			reservation.setDelai(voitureRequest.delay());
			reservation.setUser(user);
			reservation.setVoiture(voiture);
			reservation.setId(new KeyReservation(user.getUsername(), voiture.getId()));
			reservationRepository.save(reservation);
		}
		return ResponseEntity.accepted().build();
	}
}
