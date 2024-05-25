package com.vehicle.vehicle_information.service.impl;

import com.vehicle.vehicle_information.entity.Vehicle;
import com.vehicle.vehicle_information.exception.DataNotFoundException;
import com.vehicle.vehicle_information.mapper.VehicleMapper;
import com.vehicle.vehicle_information.model.VehicleDto;
import com.vehicle.vehicle_information.repository.VehicleRepository;
import com.vehicle.vehicle_information.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public VehicleDto findVehicle(int id) {
        if(id==0){
            throw new DataNotFoundException("No Id found");
        }
        List<String> colour= Arrays.asList("Black","Grey");
        return VehicleDto.builder().rto("Pune").brand("TVS").owner("Ravi").regNo(id).colours(colour).build();

    }

    @Override
    public VehicleDto saveVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle=VehicleMapper.MapToVehicle(vehicleDto);
        vehicle.setCreatedBy("Admin");
        vehicle.setCreatedOn(Date.valueOf(LocalDate.now()));
        vehicleRepository.save(vehicle);
        vehicleDto.setRegNo(vehicle.getRegNo());

        return vehicleDto;
    }
}
