package com.daniel.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.flightreservation.entities.Role;

public interface RoleRespository extends JpaRepository<Role, Long> {

}
