package com.daniel.flightreservation.controllers;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daniel.flightreservation.entities.Flight;
import com.daniel.flightreservation.repos.FlightRepository;

@Controller
public class FlightController {
	
	@Autowired
	FlightRepository flightRepository;
	
private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);
	

	@RequestMapping("/findFlights")
	public String findFlights(@RequestParam("from")String from, @RequestParam("to") String to, @RequestParam("departureDate") @DateTimeFormat(pattern="MM-dd-yy") Date departureDate, ModelMap modelMap) {
		
		LOGGER.info("inside findFlights() Frome: " + from + " TO: " + to + "Departure Date: " + departureDate);
		
		
		List<Flight> flights = flightRepository.findFlights(from,to,departureDate);
		modelMap.addAttribute("flights", flights);
		
		LOGGER.info("Flights Found are: " + flights);
		return "displayFlights";
	}
	
	@RequestMapping("admin/showAddFlight")
	public String showAddFlight() {
		return "addFlight";
	}
}
