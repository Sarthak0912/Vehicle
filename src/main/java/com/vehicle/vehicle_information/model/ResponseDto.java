package com.vehicle.vehicle_information.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResponseDto <T>{

    String message;
    int statusCode;

    T Data;

    List<T> vehicles;

}
