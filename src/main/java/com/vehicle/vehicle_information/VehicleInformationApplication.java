package com.vehicle.vehicle_information;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class VehicleInformationApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleInformationApplication.class, args);
	}

}
