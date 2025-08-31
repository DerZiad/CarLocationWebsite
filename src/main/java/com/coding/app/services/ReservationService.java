package com.coding.app.services;

import com.coding.app.models.Reservation;
import com.coding.app.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
}
