package br.ufrn.smartSecuritySpring.repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import br.ufrn.smartSecuritySpring.model.PoliceLocation;

public class SmartSecurityConfig {

	@Bean
	CommandLineRunner commandLineRunner(SmartSecurityRepository repository) {
		return args -> {
			PoliceLocation policeLocation1 = new PoliceLocation(
				// The id is generated for us by the database
				Long.valueOf(201988702),
				Long.valueOf(1),
				"RN",
				LocalDate.of(2021, Month.NOVEMBER, 19),
				-6.9764,
				-43.5591,
				"ABC-1234"
			);
			
			PoliceLocation policeLocation2 = new PoliceLocation(
				Long.valueOf(201800608),
				Long.valueOf(1),
				"RN",
				LocalDate.of(2021, Month.NOVEMBER, 19),
				-5.8783,
				-35.2094,
				"OJK-9999"
			);
			
			// Save into our database (Hibernate runs when we invoke saveAll)
			repository.saveAll(
				List.of(policeLocation1, policeLocation2)
			);
		};
	}
}
