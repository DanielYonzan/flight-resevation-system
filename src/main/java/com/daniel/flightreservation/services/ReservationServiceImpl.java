package com.daniel.flightreservation.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.daniel.flightreservation.controllers.UserController;
import com.daniel.flightreservation.dto.ReservationRequest;
import com.daniel.flightreservation.entities.Flight;
import com.daniel.flightreservation.entities.Passenger;
import com.daniel.flightreservation.entities.Reservation;
import com.daniel.flightreservation.repos.FlightRepository;
import com.daniel.flightreservation.repos.PassengerRepository;
import com.daniel.flightreservation.repos.ReservationRepository;
import com.daniel.flightreservation.util.EmailUtil;
import com.daniel.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${D:\\Reservations}")
	private String ITINERARY_DIR;;

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		
		LOGGER.info("Inside bookFlight()");
		
		// Make Payment
		
		Long flightId = request.getFlightId();
		
		LOGGER.info("Fetching flight for flight id:" + flightId);
		
		Flight flight = flightRepository.findById(flightId).orElse(null);
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setMiddleName(request.getPassengerMiddleName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		Reservation savedReservation = reservationRepository.save(reservation);
		
		String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";
		LOGGER.info("Generating the itinerary");
		pdfGenerator.generateItinerary(savedReservation, filePath);
		LOGGER.info("Emailing the itinerary");
		emailUtil.sentItinerary(passenger.getEmail(),filePath);
		
		return savedReservation;
	}

}
