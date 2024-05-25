package com.vehicle.vehicle_information.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Getter
@Setter
@Entity(name = "vehicle")
public class Vehicle extends BaseEntity{

    String owner;
    String brand;

    List<String> colours;

    String rto;
}
