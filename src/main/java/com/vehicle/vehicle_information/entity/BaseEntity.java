package com.vehicle.vehicle_information.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.sql.Date;


@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int regNo;

    Date createdOn;
    Date updatedOn;

    String createdBy;
    String updatedBy;
    int isDeleted;


}
