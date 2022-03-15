package com.zechariah.fleetms.fleet.service;

import com.zechariah.fleetms.fleet.model.Vehicle;
import com.zechariah.fleetms.fleet.repository.VehicleRepository;
import com.zechariah.fleetms.parameter.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicle(){
        return vehicleRepository.findAll();
    }

    public void saveVehicle(Vehicle vehicle){
        vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(int id){
        vehicleRepository.deleteById(id);
    }

    public Vehicle editVehicle(int id){
        return vehicleRepository.findById(id).orElse(null);
    }

    //    Pagination
    public Page<Vehicle> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return vehicleRepository.findAll(pageable);
    }

    //        Sort the Client Table
    public Page<Vehicle> findClientWithSorting(String field, String direction, int pageNumber){
//        Sorting Client By Direction with an If Statement
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
//        Adding the Sorted Content into making it Pageable
        Pageable pageable = PageRequest.of(pageNumber - 1,5, sort);
        return vehicleRepository.findAll(pageable);
    }
}
