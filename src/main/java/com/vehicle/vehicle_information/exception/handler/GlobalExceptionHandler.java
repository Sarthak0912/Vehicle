package com.vehicle.vehicle_information.exception.handler;

import com.vehicle.vehicle_information.exception.DataNotFoundException;
import com.vehicle.vehicle_information.model.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleVehicleNotFoundException(DataNotFoundException exception){
         return new ResponseEntity<>(ResponseDto.builder().message(exception.getMessage()).statusCode(HttpStatus.NOT_FOUND.value()).build(),HttpStatus.NOT_FOUND);
    }



}
