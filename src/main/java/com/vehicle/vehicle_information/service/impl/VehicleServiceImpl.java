package com.vehicle.vehicle_information.service.impl;

import com.vehicle.vehicle_information.entity.Vehicle;
import com.vehicle.vehicle_information.exception.DataNotFoundException;
import com.vehicle.vehicle_information.mapper.VehicleMapper;
import com.vehicle.vehicle_information.model.PageResponse;
import com.vehicle.vehicle_information.model.VehicleDto;
import com.vehicle.vehicle_information.repository.VehicleRepository;
import com.vehicle.vehicle_information.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public VehicleDto findVehicle(int id) {
        Optional<Vehicle> optionalVehicle= vehicleRepository.findByRegNoAndIsDeleted(id, 0);

        if(optionalVehicle.isPresent()){

            Vehicle vehicle= optionalVehicle.get();
            return VehicleMapper.MapToVehicleDto(vehicle);
        }

        else{
            throw new DataNotFoundException("No Id found");
        }
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

    @Override
    public VehicleDto deleteVehicle(int id) {

        Optional<Vehicle> optionalVehicle= vehicleRepository.findByRegNoAndIsDeleted(id, 0);
        if(optionalVehicle.isPresent()){

            Vehicle vehicle= optionalVehicle.get();
            vehicle.setIsDeleted(1);
            vehicleRepository.save(vehicle);
            return VehicleMapper.MapToVehicleDto(vehicle);
        }

        else{
            throw new DataNotFoundException("No Id found");
        }
    }

    @Override
    public VehicleDto updateVehicle(VehicleDto vehicleDto) {

        Optional<Vehicle> optionalVehicle= vehicleRepository.findByRegNoAndIsDeleted(vehicleDto.getRegNo(), 0);
        if(optionalVehicle.isPresent()){

            Vehicle vehicle= optionalVehicle.get();
           if(!vehicleDto.getOwner().isEmpty()) {
           vehicle.setOwner(vehicleDto.getOwner());
           }
           vehicle.setUpdatedBy("Admin");
           vehicle.setUpdatedOn(Date.valueOf(LocalDate.now()));
            vehicleRepository.save(vehicle);
            return VehicleMapper.MapToVehicleDto(vehicle);
        }

        else{
            throw new DataNotFoundException("No Id found");
        }

    }

    @Override
    public List<VehicleDto> findVehicleBySearchText(String searchText) {
        List<Vehicle> vehicles=vehicleRepository.findVehicleBySearchText(searchText);
        return vehicles.stream().map(VehicleMapper::MapToVehicleDto).collect(Collectors.toList());
    }

    @Override
    public PageResponse<?> findVehicleInPages(int pageNo, int pageSize, String sortBy, String sortDir) {



        return null;
    }


}
