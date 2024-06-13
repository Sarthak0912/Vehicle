package com.vehicle.vehicle_information.controller;

import com.vehicle.vehicle_information.model.ResponseDto;
import com.vehicle.vehicle_information.model.VehicleDto;
import com.vehicle.vehicle_information.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@Tag(name="Vehicle Controller",description = "It gives vehicle Information.")
@RequestMapping(value = "/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;
    @Autowired
    VehicleController(VehicleService vehicleService){
        this.vehicleService=vehicleService;
    }

    @Operation(summary = "Used for getting details using registration number.")
    @GetMapping (value = "{vehicleId}")
    public ResponseEntity<ResponseDto<Object>> getVehicleByPathVariable(@PathVariable int vehicleId){

        return new ResponseEntity<>(ResponseDto.builder().message("Found data").statusCode(HttpStatus.FOUND.value()).Data(vehicleService.findVehicle(vehicleId)).build(),HttpStatus.FOUND);

    }


    @GetMapping
    public ResponseEntity<ResponseDto<Object>> getVehicleByRP(@RequestParam("VID") int id){

        return new ResponseEntity<>(ResponseDto.builder().message("Found data!").statusCode(HttpStatus.FOUND.value()).Data(vehicleService.findVehicle(id)).build(),HttpStatus.FOUND);

    }

    @PostMapping
    public ResponseEntity<ResponseDto<Object>> saveVehicleInfo(@RequestBody VehicleDto vehicleDto){

        return new ResponseEntity<>(ResponseDto.builder().message("Data Saved!!").statusCode(HttpStatus.CREATED.value()).Data(vehicleService.saveVehicle(vehicleDto)).build(),HttpStatus.CREATED);

    }

    @DeleteMapping("{vehicleId}")
    public ResponseEntity<ResponseDto<Object>> deleteVehicleInfo(@PathVariable int vehicleId){

        return new ResponseEntity<>(ResponseDto.builder().message("Data deleted!!").statusCode(HttpStatus.OK .value()).Data(vehicleService.deleteVehicle(vehicleId)).build(),HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<ResponseDto<Object>> updateVehicleInfo(@RequestBody VehicleDto vehicleDto){

        return new ResponseEntity<>(ResponseDto.builder().message("Data Updated!!").statusCode(HttpStatus.ACCEPTED.value()).Data(vehicleService.updateVehicle(vehicleDto)).build(),HttpStatus.ACCEPTED);

    }

    @GetMapping("search")
    public ResponseEntity<ResponseDto<Object>> searchVehicleByText(@RequestParam("SearchText") String searchText){

        return new ResponseEntity<>(ResponseDto.builder().message("Data Searched").statusCode(HttpStatus.FOUND.value()).vehicles(Collections.singletonList(vehicleService.findVehicleBySearchText(searchText))).build(),HttpStatus.FOUND);

    }

    @GetMapping("searchAll")
    public ResponseEntity<ResponseDto<Object>> searchVehicleByPagination(@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize,@RequestParam("sortDir") String sortDir,@RequestParam("sortBy") String sortBy){

        return new ResponseEntity<>(ResponseDto.builder().message("Data Searched!!").statusCode(HttpStatus.FOUND.value()).vehicles(Collections.singletonList(vehicleService.findAll(pageNo, pageSize, sortBy, sortDir))).build(),HttpStatus.FOUND);

    }

    @GetMapping("searchPage")
    public ResponseEntity<ResponseDto<Object>> searchAll(@RequestParam("SearchText") String searchText,@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize,@RequestParam("sortDir") String sortDir,@RequestParam("sortBy") String sortBy){

        return new ResponseEntity<>(ResponseDto.builder().message("Data Searched!!").statusCode(HttpStatus.FOUND.value()).vehicles(Collections.singletonList(vehicleService.findAllWithPagination(searchText,pageNo,pageSize,sortBy,sortDir))).build(),HttpStatus.FOUND);

    }




}
