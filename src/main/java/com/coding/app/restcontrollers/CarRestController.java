package com.coding.app.restcontrollers;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.app.dto.CarRequest;
import com.coding.app.exceptions.NotFoundException;
import com.coding.app.models.Car;
import com.coding.app.models.Reservation;
import com.coding.app.models.User;
import com.coding.app.models.key.KeyReservation;
import com.coding.app.repository.CarRepository;
import com.coding.app.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(name = "/api/cars", produces = MediaType.APPLICATION_JSON_VALUE, value = "/api/cars")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class CarRestController {

	private final CarRepository carRepository;

	private final ReservationRepository reservationRepository;
	
	@GetMapping
	public HttpEntity<?> getCars() {
		return ResponseEntity.ok(carRepository.findAll());
	}

	@PostMapping
	public HttpEntity<?> addVoiture(@RequestBody CarRequest voitureRequest) throws NotFoundException {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			System.out.println("Traitement");
			Car car = carRepository.findById(voitureRequest.carId())
					.orElseThrow(() -> new NotFoundException("Voiture not found"));
			User user = (User) auth.getPrincipal();
			Reservation reservation = new Reservation();
			System.out.println(voitureRequest.delay());
			reservation.setDelai(voitureRequest.delay());
			reservation.setUser(user);
			reservation.setCar(car);
			reservation.setId(new KeyReservation(user.getUsername(), car.getId()));
			reservationRepository.save(reservation);
		}
		return ResponseEntity.accepted().build();
	}
}
