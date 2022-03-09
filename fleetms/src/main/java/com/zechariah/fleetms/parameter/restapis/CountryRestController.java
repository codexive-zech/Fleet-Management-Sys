package com.zechariah.fleetms.parameter.restapis;

import com.zechariah.fleetms.parameter.models.Country;
import com.zechariah.fleetms.parameter.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryRestController {

    @Autowired
    private CountryService countryService;

    @CrossOrigin
    @GetMapping("/api/countries")
    public List<Country> getCountries(){
        List<Country> countries = countryService.getAllCountry();
        return countries;
    }
}
