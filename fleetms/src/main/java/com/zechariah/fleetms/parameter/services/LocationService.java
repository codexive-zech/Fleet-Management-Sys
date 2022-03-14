package com.zechariah.fleetms.parameter.services;

import com.zechariah.fleetms.parameter.models.Country;
import com.zechariah.fleetms.parameter.models.Location;
import com.zechariah.fleetms.parameter.models.State;
import com.zechariah.fleetms.parameter.repositories.LocationRepository;
import com.zechariah.fleetms.parameter.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    //        Sort the Country Table
    public Page<Location> findClientWithSorting(String field, String direction, int pageNumber){
//        Sorting Country By Direction with an If Statement
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
//        Adding the Sorted Content into making it Pageable
        Pageable pageable = PageRequest.of(pageNumber - 1,5, sort);
        return locationRepository.findAll(pageable);
    }

    public Page<Location> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return locationRepository.findAll(pageable);
    }
}
