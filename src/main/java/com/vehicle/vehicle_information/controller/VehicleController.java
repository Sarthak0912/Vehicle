package com.vehicle.vehicle_information.controller;

import com.vehicle.vehicle_information.model.ResponseDto;
import com.vehicle.vehicle_information.model.VehicleDto;
import com.vehicle.vehicle_information.service.VehicleService;
import com.vehicle.vehicle_information.service.impl.VehicleServiceImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping(value = "/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping (value = "{vehicleId}")
    public ResponseEntity<?> getVehicleByPathVariable(@PathVariable int vehicleId){

        return new ResponseEntity<>(ResponseDto.builder().message("Found data").statusCode(HttpStatus.FOUND.value()).Data(vehicleService.findVehicle(vehicleId)).build(),HttpStatus.FOUND);

    }

    @GetMapping
    public ResponseEntity<?> getVehicleByRP(@RequestParam("VID") int id){

        return new ResponseEntity<>(ResponseDto.builder().message("Found data").statusCode(HttpStatus.FOUND.value()).Data(vehicleService.findVehicle(id)).build(),HttpStatus.FOUND);

    }

    @PostMapping
    public ResponseEntity<?> saveVehicleInfo(@RequestBody VehicleDto vehicleDto){

        return new ResponseEntity<>(ResponseDto.builder().message("Data Saved!!").statusCode(HttpStatus.CREATED.value()).Data(vehicleService.saveVehicle(vehicleDto)).build(),HttpStatus.CREATED);

    }

    @DeleteMapping("{vehicleId}")
    public ResponseEntity<?> deleteVehicleInfo(@PathVariable int vehicleId){

        return new ResponseEntity<>(ResponseDto.builder().message("Data deleted!!").statusCode(HttpStatus.OK .value()).Data(vehicleService.deleteVehicle(vehicleId)).build(),HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<?> updateVehicleInfo(@RequestBody VehicleDto vehicleDto){

        return new ResponseEntity<>(ResponseDto.builder().message("Data Updated!!").statusCode(HttpStatus.ACCEPTED.value()).Data(vehicleService.updateVehicle(vehicleDto)).build(),HttpStatus.ACCEPTED);

    }

    @GetMapping("search")
    public ResponseEntity<?> searchVehicleByText(@RequestParam("SearchText") String searchText){

        return new ResponseEntity<>(ResponseDto.builder().message("Data Searched!!").statusCode(HttpStatus.FOUND.value()).vehicles(Collections.singletonList(vehicleService.findVehicleBySearchText(searchText))).build(),HttpStatus.FOUND);

    }




}
