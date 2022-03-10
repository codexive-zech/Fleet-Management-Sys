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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    public String viewLocation(Model model, String keyword){
        //        Declaring Location List
        List<Location> locations;
        //    Checking to see if Keyword is null or not.
        if (keyword == null){
            locations = locationService.getLocations();
        } else {
            locations = locationService.findByKeyword(keyword);
        }
        //      Displaying Location List in the web Page
        getModel(model);
        model.addAttribute("locations", locations);
        return "/parameter/locationList";
    }

    @GetMapping("/locationAdd")
    public String getNewLocation(Model model){
        model.addAttribute("countries", countryService.getAllCountry());
        return "/parameter/locationAdd";
    }

    @PostMapping("/locations")
    public String addLocation(Location location, RedirectAttributes redirectAttributes){
        locationService.saveLocation(location);
        redirectAttributes.addFlashAttribute("message", "Location Has Been Added Successfully");
        return "redirect:/locations";
    }

    @RequestMapping(value = "/locations/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteState(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        locationService.deleteLocation(id);
        redirectAttributes.addFlashAttribute("message", "Location Has Been Deleted Successfully");
        return "redirect:/locations";
    }

    @GetMapping("locationEdit/{id}")
    public String getEditState(@PathVariable Integer id, Model model){
        getModel(model);
        model.addAttribute("location", locationService.editLocation(id));
        return "parameter/locationEdit";
    }

    @RequestMapping(value = "/location/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editState(Location location, RedirectAttributes redirectAttributes){
        locationService.saveLocation(location);
        redirectAttributes.addFlashAttribute("message", "Location Has Been Updated Successfully");
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
