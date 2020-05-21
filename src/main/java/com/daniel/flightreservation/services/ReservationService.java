package com.daniel.flightreservation.services;

import com.daniel.flightreservation.dto.ReservationRequest;
import com.daniel.flightreservation.entities.Reservation;

public interface ReservationService {
	
	public Reservation bookFlight(ReservationRequest request);

}
