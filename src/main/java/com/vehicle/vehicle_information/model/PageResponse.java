package com.vehicle.vehicle_information.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse <T>{

    String message;
    int statusCode;

    List<T> vehicles;

    int pageSize;
    int pageNo;
    long totalPages;
    Boolean isLast;

}
