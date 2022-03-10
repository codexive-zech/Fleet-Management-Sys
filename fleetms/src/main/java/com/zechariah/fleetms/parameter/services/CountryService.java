package com.zechariah.fleetms.parameter.services;

import com.zechariah.fleetms.parameter.models.Country;
import com.zechariah.fleetms.parameter.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountry(){
        return countryRepository.findAll();
    }

    public void saveCountry(Country country){
        countryRepository.save(country);
    }

    public void deleteCountry(Integer id){
        countryRepository.deleteById(id);
    }

    public Country editCountry(Integer id) {
        return countryRepository.findById(id).orElse(null);
    }
}
