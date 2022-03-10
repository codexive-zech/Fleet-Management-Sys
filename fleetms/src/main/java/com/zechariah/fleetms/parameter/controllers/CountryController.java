package com.zechariah.fleetms.parameter.controllers;

import com.zechariah.fleetms.parameter.models.Country;
import com.zechariah.fleetms.parameter.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;


// Display All Country

    @GetMapping("/countries")
    public String getAllCountry(Model model, String keyword){
//        Declaring Country List
      List<Country> countries;

//    Checking to see if Keyword is null or not.
      if (keyword == null){
         countries = countryService.getAllCountry();
      } else {
          countries = countryService.findByKeyword(keyword);
      }

//      Displaying Country List in the web Page
      model.addAttribute("countries", countries);
        return "parameter/countryList";
    }

// Display The Add Country Form Web Page

    @GetMapping("/countryAdd")
    public String getNewCountry(){
        return "parameter/countryAdd";
    }

    //The Get Country By Id
    @GetMapping("/parameters/country/{id}")
    @ResponseBody
    public Country getCountry(@PathVariable Integer id){
        return countryService.editCountry(id);
    }

    @PostMapping("/countries")
    public String saveCountry(Country country, RedirectAttributes redirectAttributes){
        countryService.saveCountry(country);
        redirectAttributes.addFlashAttribute("message", "This Country has been saved Successfully.");
        return "redirect:/countries";
    }

    @RequestMapping(value ="/countries/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteCountry(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        countryService.deleteCountry(id);
        redirectAttributes.addFlashAttribute("message", "Country has been Deleted Successfully.");
        return "redirect:/countries";
    }

    @GetMapping("/countryEdit/{id}")
    public String editCountry(@PathVariable Integer id, Model model){
        Country country = countryService.editCountry(id);
        model.addAttribute("country", country);
        return "parameter/countryEdit";
    }

    @RequestMapping(value = "/countries/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editCountry(Country country, RedirectAttributes redirectAttributes){
        countryService.saveCountry(country);
        redirectAttributes.addFlashAttribute("message", "Country Has Been Updated Successfully.");
        return "redirect:/countries";
    }

//    Display Country Details

    @GetMapping("/countryDetails/{id}")
    public String getCountryDetails(@PathVariable Integer id, Model model){
        Country country = countryService.editCountry(id);
        model.addAttribute("country", country);
        return "parameter/countryDetails";
    }
}
