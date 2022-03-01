package com.zechariah.fleetms.parameter.controllers;

import com.zechariah.fleetms.parameter.models.Country;
import com.zechariah.fleetms.parameter.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/countries")
    public String getAllCountry(Model model){
      List<Country> countries = countryService.getAllCountry();
      model.addAttribute("countries", countries);
        return "parameter/countryList";
    }

    @GetMapping("/countryAdd")
    public String getNewCountry(){
        return "parameter/countryAdd";
    }
}
