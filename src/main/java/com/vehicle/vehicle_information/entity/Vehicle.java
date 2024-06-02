package com.vehicle.vehicle_information.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;


import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "vehicle")
public class Vehicle extends BaseEntity{

    String owner;
    String brand;

    String colours;

    String rto;
}
