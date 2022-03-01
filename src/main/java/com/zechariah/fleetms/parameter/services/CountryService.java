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
}
