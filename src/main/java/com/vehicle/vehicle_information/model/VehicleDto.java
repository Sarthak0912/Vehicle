package com.vehicle.vehicle_information.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class VehicleDto {

    int regNo;
    Date createdOn;
    Date updatedOn;

    String createdBy;
    String updatedBy;
    int isDeleted;

    String owner;
    String brand;

    List<String> colours;

    String rto;






}
