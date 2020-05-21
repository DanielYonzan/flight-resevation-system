package com.daniel.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.flightreservation.entities.Passenger;



public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
