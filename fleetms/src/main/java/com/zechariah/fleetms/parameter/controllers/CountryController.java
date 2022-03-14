package com.zechariah.fleetms.parameter.controllers;

import com.zechariah.fleetms.parameter.models.Client;
import com.zechariah.fleetms.parameter.models.Country;
import com.zechariah.fleetms.parameter.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

    //    Displaying the List of Country in the webpage
//    @GetMapping("/countries")
//    public String getAllCountry(Model model, String keyword){
////        Declaring Country List
//      List<Country> countries;
////    Checking to see if Keyword is null or not.
//      if (keyword == null){
//         countries = countryService.getAllCountry();
//      } else {
//          countries = countryService.findByKeyword(keyword);
//      }
////      Displaying Country List in the web Page
//      model.addAttribute("countries", countries);
//        return "parameter/countryList";
//    }

//    This endpoint will be triggered when no page number is given so this is the first page available
    @GetMapping("/countries")
    public String getAllPages(Model model){
        return getOnePage(model, 1);
    }


//    Displaying the List of Country in the webpage via Page
    @GetMapping("/countries/page/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
//        Getting the Pageable Countries
        Page<Country> page = countryService.findPage(currentPage);
//        Retrieving The Total Number of Pages Available
        int totalPages = page.getTotalPages();
//        Retrieving The Total Number of Pages Available
        long totalItems = page.getTotalElements();
//        Retrieving the total list of countries available
        List<Country> countries = page.getContent();
//      Displaying Country List in the web Page
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("countries", countries);
        return "parameter/countryList";
    }

    //    Displaying Country Sorting and Paging into the webpage
    @GetMapping("/countries/page/{pageNumber}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable("pageNumber") int currentPage,
                                  @PathVariable String field,
                                  @PathParam("sortDir") String sortDir){

//        Getting the Country Sorting and page available from the service
        Page<Country> page = countryService.findClientWithSorting(field, sortDir, currentPage);

//        Display the list of Country in a Page Format
        List<Country> countries = page.getContent();
        //        Retrieving The Total Number of Pages Available
        int totalPages = page.getTotalPages();
        //        Retrieving The Total Number of Items Available
        long totalItems = page.getTotalElements();

        //      Displaying Country List in the web Page
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("countries", countries);
        return "/parameter/countryList";
    }

    //    Displaying the Webpage of Add Form for Country
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

    //    Saving the Information in the Form to the Database and Displaying the List back
    @PostMapping("/countries")
    public String saveCountry(Country country, RedirectAttributes redirectAttributes){
        countryService.saveCountry(country);
        redirectAttributes.addFlashAttribute("message", "This Country has been saved Successfully.");
        return "redirect:/countries";
    }

    //    Deleting and Displaying a Country in the webpage
    @RequestMapping(value ="/countries/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteCountry(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        countryService.deleteCountry(id);
        redirectAttributes.addFlashAttribute("message", "Country has been Deleted Successfully.");
        return "redirect:/countries";
    }

    //    Display a particular Country to edit
    @GetMapping("/countryEdit/{id}")
    public String editCountry(@PathVariable Integer id, Model model){
        Country country = countryService.editCountry(id);
        model.addAttribute("country", country);
        return "parameter/countryEdit";
    }

    //    Saving a particular Country after edit and Displaying to the webpage
    @RequestMapping(value = "/countries/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editCountry(Country country, RedirectAttributes redirectAttributes){
        countryService.saveCountry(country);
        redirectAttributes.addFlashAttribute("message", "Country Has Been Updated Successfully.");
        return "redirect:/countries";
    }

    //    Displaying Country Details on the webpage
    @GetMapping("/countryDetails/{id}")
    public String getCountryDetails(@PathVariable Integer id, Model model){
        Country country = countryService.editCountry(id);
        model.addAttribute("country", country);
        return "parameter/countryDetails";
    }
}
