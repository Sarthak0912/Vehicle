package com.vehicle.vehicle_information.mapper;

import com.vehicle.vehicle_information.entity.Vehicle;
import com.vehicle.vehicle_information.model.VehicleDto;

public class VehicleMapper {

    public  static Vehicle MapToVehicle(VehicleDto vehicleDto) {

        Vehicle vehicle=new Vehicle();
        vehicle.setColours(vehicleDto.getColours());
        vehicle.setRto(vehicleDto.getRto());
        vehicle.setBrand(vehicleDto.getBrand());
        vehicle.setOwner(vehicleDto.getOwner());

        return vehicle;
    }



}
