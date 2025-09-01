package com.coding.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.coding.app.exceptions.NotFoundException;
import com.coding.app.models.Reservation;
import com.coding.app.models.key.KeyReservation;
import com.coding.app.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getReservationsByFilter(Predicate<Reservation> filter) {
        return reservationRepository.findAll().stream().filter(filter).collect(Collectors.toCollection(ArrayList::new));
    }

    public void acceptReservation(final Long carId, final String username) throws NotFoundException {
        final KeyReservation id = new KeyReservation(username, carId);
        final Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException("Reservation not found"));
        if(reservation != null) {
        	reservation.setConfirmed(true);
        	reservationRepository.save(reservation);
        }
    }
}
