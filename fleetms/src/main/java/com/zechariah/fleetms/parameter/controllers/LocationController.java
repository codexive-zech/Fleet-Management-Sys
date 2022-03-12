package com.zechariah.fleetms.parameter.controllers;


import com.zechariah.fleetms.parameter.models.Location;
import com.zechariah.fleetms.parameter.models.State;
import com.zechariah.fleetms.parameter.services.CountryService;
import com.zechariah.fleetms.parameter.services.LocationService;
import com.zechariah.fleetms.parameter.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    //    Getting all The Details meant to be rendered on the webpage
    public Model getModel(Model model){
        model.addAttribute("states", stateService.getStates());
        model.addAttribute("countries", countryService.getAllCountry());
        model.addAttribute("locations", locationService.getLocations());
        return model;
    }

//    //    Displaying the List of Location in the webpage
//    @GetMapping("/locations")
//    public String viewLocation(Model model, String keyword){
//        //        Declaring Location List
//        List<Location> locations;
//        //    Checking to see if Keyword is null or not.
//        if (keyword == null){
//            locations = locationService.getLocations();
//        } else {
//            locations = locationService.findByKeyword(keyword);
//        }
//        //      Displaying Location List in the web Page
//        getModel(model);
//        model.addAttribute("locations", locations);
//        return "/parameter/locationList";
//    }

    //    This endpoint will be triggered when no page number is given so this is the first page available
    @GetMapping("/locations")
    public String getLocation(Model model){
        return getOneLocation(model, 1);
    }

    //    Displaying the List of Country in the webpage via Page
    @GetMapping("/locations/page/{pageNumber}")
    public String getOneLocation(Model model, @PathVariable ("pageNumber") int currentPage){
        //        Getting the Pageable Countries
        Page<Location> page = locationService.findPage(currentPage);
        //        Retrieving The Total Number of Pages Available
        int totalPages = page.getTotalPages();
        //        Retrieving The Total Number of Pages Available
        long totalItems = page.getTotalElements();
        //        Retrieving the total list of countries available
        List<Location> locations = page.getContent();
        //      Displaying Location List in the web Page
        getModel(model);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("locations", locations);
        return "/parameter/locationList";
    }
//    *********************

    //    Displaying Location Sorting into the webpage
    @GetMapping("/locations/{field}")
    public String viewLocationWithSort(Model model,@PathVariable String field){
        //        Declaring Location List
        List<Location> locations;
   //    Sorting the Location Table via Field
        locations = locationService.getLocationsWithSort(field);
        //      Displaying Location List in the web Page
        getModel(model);
        model.addAttribute("locations", locations);
        return "/parameter/locationList";
    }

    //    Displaying the Webpage of Add Form for Location
    @GetMapping("/locationAdd")
    public String getNewLocation(Model model){
        model.addAttribute("countries", countryService.getAllCountry());
        return "/parameter/locationAdd";
    }

    //    Saving the Information in the Form to the Database and Displaying the List back
    @PostMapping("/locations")
    public String addLocation(Location location, RedirectAttributes redirectAttributes){
        locationService.saveLocation(location);
        redirectAttributes.addFlashAttribute("message", "Location Has Been Added Successfully");
        return "redirect:/locations";
    }

    //    Deleting and Displaying a Location in the webpage
    @RequestMapping(value = "/locations/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteState(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        locationService.deleteLocation(id);
        redirectAttributes.addFlashAttribute("message", "Location Has Been Deleted Successfully");
        return "redirect:/locations";
    }

    //    Display a particular Location to edit
    @GetMapping("locationEdit/{id}")
    public String getEditState(@PathVariable Integer id, Model model){
        getModel(model);
        model.addAttribute("location", locationService.editLocation(id));
        return "parameter/locationEdit";
    }

    //    Saving a particular Location after edit and Displaying to the webpage
    @RequestMapping(value = "/location/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editState(Location location, RedirectAttributes redirectAttributes){
        locationService.saveLocation(location);
        redirectAttributes.addFlashAttribute("message", "Location Has Been Updated Successfully");
        return "redirect:/locations";
    }

//    Displaying Location Details on the webpage
    @GetMapping("/locationDetails/{id}")
    public String getStateDetails(@PathVariable Integer id, Model model){
        Location location = locationService.editLocation(id);
        model.addAttribute("location", location);
        return "parameter/locationDetails";
    }
}
