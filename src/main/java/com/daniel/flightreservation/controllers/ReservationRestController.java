package com.daniel.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.flightreservation.dto.ReservationUpdateRequest;
import com.daniel.flightreservation.entities.Reservation;
import com.daniel.flightreservation.repos.ReservationRepository;

@RestController
@CrossOrigin
public class ReservationRestController {

	@Autowired
	ReservationRepository reservationRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id")Long id) {
		LOGGER.info(" Inside the findReservation() for id: " + id);
		return reservationRepository.findById(id).orElse(null);
	}
	
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		LOGGER.info(" Inside updateReservation() for " + request);
		Reservation reservation = reservationRepository.findById(request.getId()).orElse(null);
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		LOGGER.info("Saving Reservation ");
		return reservationRepository.save(reservation);
	}
}
