package com.coding.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coding.app.models.Reservation;
import com.coding.app.models.key.KeyReservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, KeyReservation> {

}
