package br.ufrn.smartSecuritySpring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.smartSecuritySpring.model.PoliceLocation;

//JpaRepository<type that we want to work with, id for the type that we want>
@Repository // Interface responsible for data access
public interface SmartSecurityRepository extends JpaRepository<PoliceLocation, Long> {
	// Will be used inside our service with Dependency Injection

	// Function to find a team by police registry for Business Logic
//		@Query("SELECT s FROM PoliceLocation s WHERE s.policeRegistry = ?1")
	// "SELECT * FROM policeLocation WHERE policeRegistry = ?":
	Optional<PoliceLocation> findTeamByPoliceRegistry(Long policeRegistry);
}

/*
 * sudo -u postgres psql
 * \l
 * create database smart_security;
 * grant all privileges on database "smart_security" to postgres;
 * grant all privileges on database "smart_security" to camiladuartes_;
 * \c smart_security
 * \d
 * \d police_location
 * select * from police_location;
 */