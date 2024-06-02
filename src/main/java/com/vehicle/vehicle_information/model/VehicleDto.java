package com.vehicle.vehicle_information.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {

    int regNo;
    Date createdOn;
    Date updatedOn;

    String createdBy;
    String updatedBy;
    int isDeleted;

    String owner;
    String brand;

    String colours;

    String rto;






}
