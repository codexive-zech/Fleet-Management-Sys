package com.zechariah.fleetms.parameter.services;

import com.zechariah.fleetms.parameter.models.Location;
import com.zechariah.fleetms.parameter.models.State;
import com.zechariah.fleetms.parameter.repositories.LocationRepository;
import com.zechariah.fleetms.parameter.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getLocations(){
        return locationRepository.findAll();
    }

    public void saveLocation(Location location){
        locationRepository.save(location);
    }

    public void deleteLocation(Integer id) {
        locationRepository.deleteById(id);
    }

    public Location editLocation(Integer id){
        return locationRepository.findById(id).orElse(null);
    }

    //    Search Filter Location Table
    public List<Location> findByKeyword(String keyword){
        return locationRepository.findByKeyword(keyword);
    }

    //        Sort the Location Table
    public List<Location> getLocationsWithSort(String field){
        return locationRepository.findAll(Sort.by(field));
    }
}
