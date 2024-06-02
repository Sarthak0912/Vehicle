package com.vehicle.vehicle_information.service;

import com.vehicle.vehicle_information.entity.Vehicle;
import com.vehicle.vehicle_information.model.PageResponse;
import com.vehicle.vehicle_information.model.VehicleDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VehicleService {


    public VehicleDto findVehicle( int id);

    public VehicleDto saveVehicle(VehicleDto vehicleDto);

    public VehicleDto deleteVehicle( int id);

    public VehicleDto updateVehicle( VehicleDto vehicleDto);

    public List<VehicleDto> findVehicleBySearchText(String searchText);

    public PageResponse<?> findAll(int pageNo,int pageSize,String sortBy,String sortDir);

    public PageResponse<?> findAllWithPagination(String searchText,int pageNo, int pageSize, String sortBy, String sortDir);

}
