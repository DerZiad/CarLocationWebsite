package com.coding.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.coding.app.exceptions.NotFoundException;
import com.coding.app.models.Car;
import com.coding.app.models.Reservation;
import com.coding.app.models.User;
import com.coding.app.models.enums.EmailType;
import com.coding.app.models.key.KeyReservation;
import com.coding.app.repository.ReservationRepository;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;

	private final EmailService emailService;

	private final UserService userService;

	private final CarService carService;

	public List<Reservation> getAllReservations() {
		return reservationRepository.findAll();
	}

	public List<Reservation> getReservationsByFilter(final Predicate<Reservation> filter) {
		return reservationRepository.findAll().stream().filter(filter).collect(Collectors.toCollection(ArrayList::new));
	}

	public void acceptReservation(final Long carId, final String username) throws NotFoundException {
		final KeyReservation id = new KeyReservation(username, carId);
		final Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException("Reservation not found"));
		reservation.setConfirmed(true);
		reservationRepository.save(reservation);
		final User user = userService.findByUsername(username);
		final Car car = carService.getCar(carId);
		final Map<String, String> letterBody = Map.of(
				"name", user.getUsername(),
				"informationText", "Your reservation for the vehicle " + car.getBrand().name() + " (" + car.getCategory().toString() + ") has been accepted."
		);
		sendEmailConfirmation(user.getEmail(), letterBody);
	}

	public void deleteReservation(final Long carId, final String username) throws NotFoundException {
		final KeyReservation id = new KeyReservation(username, carId);
		final Reservation reservation = reservationRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Reservation not found"));
		final User user = userService.findByUsername(username);
		final Car car = carService.getCar(carId);
		final Map<String, String> letterBody = Map.of(
			"name", user.getUsername(),
			"informationText", "Your reservation for the vehicle " + car.getBrand().name() + " (" + car.getCategory().toString() + ") has been canceled."
		);
		sendEmailConfirmation(user.getEmail(), letterBody);
		reservationRepository.delete(reservation);
	}

	private void sendEmailConfirmation(final String to, final Map<String, String> letterBody) {
		final Thread emailSend = new Thread(() -> {
			final EmailService.Email email = new EmailService.Email(to, "Reservation Accepted", EmailType.INFORMATION, letterBody);
			try {
				emailService.sendEmail(email);
			} catch (final MessagingException e) {
				e.printStackTrace();
			}

		});
		emailSend.start();
	}
}
