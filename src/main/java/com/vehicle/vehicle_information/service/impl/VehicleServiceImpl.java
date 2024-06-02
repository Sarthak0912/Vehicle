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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
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
    public PageResponse<?> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Vehicle> page = vehicleRepository.findAll(pageable);
        List<Vehicle> vehicles = page.getContent();
        List<VehicleDto> vehicleDtoList = vehicles.stream().map(VehicleMapper::MapToVehicleDto).collect(Collectors.toList());
        PageResponse<VehicleDto> pageResponse = new PageResponse<>();
        pageResponse.setPageNo(page.getNumber());
        pageResponse.setPageSize(page.getSize());
        pageResponse.setIsLast(page.isLast());
        pageResponse.setTotalPages(page.getTotalPages());
        pageResponse.setVehicles(vehicleDtoList);

        return pageResponse;
    }

    @Override
    public PageResponse<?> findAllWithPagination(String searchText,int pageNo, int pageSize, String sortBy, String sortDir) {
       Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
       Pageable pageable=PageRequest.of(pageNo,pageSize,sort);
       Page<Vehicle> page= vehicleRepository.findVehicleBySearchPagination(searchText,pageable);
       List<Vehicle> vehicles=page.getContent();
       List<VehicleDto> vehicleDtoList=vehicles.stream().map(VehicleMapper::MapToVehicleDto).collect(Collectors.toList());
       PageResponse<VehicleDto> pageResponse=new PageResponse<>();
       pageResponse.setVehicles(vehicleDtoList);
       pageResponse.setTotalPages(page.getTotalPages());
       pageResponse.setPageSize(page.getSize());
       pageResponse.setPageNo(page.getNumber());
       pageResponse.setIsLast(page.isLast());

        return pageResponse;
    }


}
