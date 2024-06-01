package com.vehicle.vehicle_information.repository;

import com.vehicle.vehicle_information.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {


//    Custom JPA

    public Optional<Vehicle> findByRegNoAndIsDeleted(int regNo,int isDeleted);

//    Custom Query
    @Query("select v from vehicle v where lower(v.owner) like lower(concat(:SearchText,'%'))")
   public List<Vehicle>  findVehicleBySearchText(@Param("SearchText") String searchText);


}
