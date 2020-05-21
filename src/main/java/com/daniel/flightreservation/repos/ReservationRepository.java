package com.daniel.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.flightreservation.entities.Reservation;



public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
