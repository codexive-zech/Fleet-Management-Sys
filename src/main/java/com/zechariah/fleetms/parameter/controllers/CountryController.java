package com.zechariah.fleetms.parameter.controllers;

import com.zechariah.fleetms.parameter.models.Country;
import com.zechariah.fleetms.parameter.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/countries")
    public String saveCountry(Country country){
        countryService.saveCountry(country);
        return "redirect:/countries";
    }

    @RequestMapping(value ="/countries/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteCountry(@PathVariable Integer id){
        countryService.deleteCountry(id);
        return "redirect:/countries";
    }

    @GetMapping("/countryEdit/{id}")
    public String editCountry(@PathVariable Integer id, Model model){
        Country country = countryService.editCountry(id);
        model.addAttribute("country", country);
        return "parameter/countryEdit";
    }

    @RequestMapping(value = "/countries/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editCountry(Country country){
        countryService.saveCountry(country);
        return "redirect:/countries";
    }
}
