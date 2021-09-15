package com.flightapp.usermode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.flightapp.usermode.DAO")
@EnableJpaRepositories(basePackages = "com.flightapp.usermode.Interface")
public class FlightAppUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightAppUserApplication.class, args);
	}

}
