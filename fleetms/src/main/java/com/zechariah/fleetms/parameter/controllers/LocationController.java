package com.zechariah.fleetms.parameter.controllers;


import com.zechariah.fleetms.parameter.models.Location;
import com.zechariah.fleetms.parameter.models.State;
import com.zechariah.fleetms.parameter.services.CountryService;
import com.zechariah.fleetms.parameter.services.LocationService;
import com.zechariah.fleetms.parameter.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LocationController {

    @Autowired
    private StateService stateService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private LocationService locationService;

    public Model getModel(Model model){
        model.addAttribute("states", stateService.getStates());
        model.addAttribute("countries", countryService.getAllCountry());
        model.addAttribute("locations", locationService.getLocations());
        return model;
    }


    @GetMapping("/locations")
    public String viewLocation(Model model){
        getModel(model);
        return "/parameter/locationList";
    }

    @GetMapping("/locationAdd")
    public String getNewLocation(Model model){
        model.addAttribute("countries", countryService.getAllCountry());
        return "/parameter/locationAdd";
    }

    @PostMapping("/locations")
    public String addLocation(Location location){
        locationService.saveLocation(location);
        return "redirect:/locations";
    }

    @RequestMapping(value = "/location/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteState(@PathVariable Integer id){
        locationService.deleteLocation(id);
        return "redirect:/location";
    }

    @GetMapping("locationEdit/{id}")
    public String getEditState(@PathVariable Integer id, Model model){
        getModel(model);
        model.addAttribute("location", locationService.editLocation(id));
        return "parameter/locationEdit";
    }

    @RequestMapping(value = "/location/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editState(Location location){
        locationService.saveLocation(location);
        return "redirect:/locations";
    }

//    Display State
    @GetMapping("/locationDetails/{id}")
    public String getStateDetails(@PathVariable Integer id, Model model){
        Location location = locationService.editLocation(id);
        model.addAttribute("location", location);
        return "parameter/locationDetails";
    }
}
