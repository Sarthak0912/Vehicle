package com.vehicle.vehicle_information.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class PageResponse <T>{

    String message;
    int statusCode;

    List<T> vehicles;

    int pageSize;
    int pageNo;
    long totalPages;
    Boolean isLast;

}
