package com.vehicle.vehicle_information.service;

import com.vehicle.vehicle_information.model.VehicleDto;

public interface VehicleService {


    public VehicleDto findVehicle( int id);

    public VehicleDto saveVehicle(VehicleDto vehicleDto);

}
