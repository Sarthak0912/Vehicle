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

    public static VehicleDto MapToVehicleDto( Vehicle vehicle){
        return VehicleDto.builder().owner(vehicle.getOwner()).brand(vehicle.getBrand()).rto(vehicle.getRto()).colours(vehicle.getColours()).regNo(vehicle.getRegNo()).createdBy(vehicle.getCreatedBy()).createdOn(vehicle.getCreatedOn()).updatedBy(vehicle.getUpdatedBy()).updatedOn(vehicle.getUpdatedOn()).build();
    }



}
