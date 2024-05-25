package com.vehicle.vehicle_information.repository;

import com.vehicle.vehicle_information.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {


}
