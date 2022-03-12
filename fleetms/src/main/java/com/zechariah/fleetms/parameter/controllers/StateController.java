package com.zechariah.fleetms.parameter.controllers;


import com.zechariah.fleetms.parameter.models.Client;
import com.zechariah.fleetms.parameter.models.Country;
import com.zechariah.fleetms.parameter.models.State;
import com.zechariah.fleetms.parameter.services.CountryService;
import com.zechariah.fleetms.parameter.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class StateController {

    @Autowired
    private StateService stateService;

    @Autowired
    private CountryService countryService;

    //    Getting all The Details meant to be rendered on the webpage
    public Model getModel(Model model){
        model.addAttribute("states", stateService.getStates());
        model.addAttribute("countries", countryService.getAllCountry());
        return model;
    }

    //    Displaying the List of State in the webpage
//    @GetMapping("/states")
//    public String viewStates(Model model, String keyword){
//        //    Declaring State List
//        List<State> states;
//        //    Checking to see if Keyword is null or not.
//        if (keyword == null){
//            states = stateService.getStates();
//        } else {
//           states = stateService.findByKeyword(keyword);
//        }
//        //    Displaying State List in the web Page
//        getModel(model);
//        model.addAttribute("states", states);
//        return "/parameter/stateList";
//    }

    //    This endpoint will be triggered when no page number is given so this is the first page available
    @GetMapping("/states")
    public String getState(Model model){
        return getOneStates(model, 1);
    }

    //    Displaying the List of Country in the webpage via Page
    @GetMapping("/states/{pageNumber}")
    public String getOneStates(Model model,@PathVariable("pageNumber") int currentPage){
        //        Getting the Pageable Countries
        Page<State> page = stateService.findPage(currentPage);
        //        Retrieving The Total Number of Pages Available
        int totalPages = page.getTotalPages();
        //        Retrieving The Total Number of Pages Available
        long totalItems = page.getTotalElements();
        //        Retrieving the total list of countries available
        List<State> states = page.getContent();
        //    Displaying State List in the web Page
        getModel(model);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("states", states);
        return "/parameter/stateList";
    }
//  ****************************

    //    Displaying State Sorting into the webpage
    @GetMapping("/states/{field}")
    public String viewStatesWithSort(Model model,@PathVariable String field){
        //    Declaring State List
        List<State> states;
        //    Sorting the State Table via Field
        states = stateService.getStatesWithSort(field);
        //    Displaying State List in the web Page
        getModel(model);
        model.addAttribute("states", states);
        return "/parameter/stateList";
    }

    //    Displaying the Webpage of Add Form for State
    @GetMapping("/stateAdd")
    public String getNewState(Model model){
        getModel(model);
        return "/parameter/stateAdd";
    }

    //    Saving the Information in the Form to the Database and Displaying the List back
    @PostMapping("/states")
    public String addState(State state, RedirectAttributes redirectAttributes){
        stateService.saveState(state);
        redirectAttributes.addFlashAttribute("message", "State Has Been Added Successfully");
        return "redirect:/states";
    }

    //    Deleting and Displaying a State in the webpage
    @RequestMapping(value = "/states/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteState(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        stateService.deleteState(id);
        redirectAttributes.addFlashAttribute("message", "State Has Been Deleted Successfully");
        return "redirect:/states";
    }

    //    Display a particular State to edit
    @GetMapping("stateEdit/{id}")
    public String getEditState(@PathVariable Integer id, Model model){
        getModel(model);
        model.addAttribute("state", stateService.editState(id));
        return "parameter/stateEdit";
    }

    //    Saving a particular State after edit and Displaying to the webpage
    @RequestMapping(value = "/state/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editState(State state, RedirectAttributes redirectAttributes){
        stateService.saveState(state);
        redirectAttributes.addFlashAttribute("message", "State Has Been Updated Successfully");
        return "redirect:/states";
    }

//    Displaying State Details on the webpage
    @GetMapping("/stateDetails/{id}")
    public String getStateDetails(@PathVariable Integer id, Model model){
        State state = stateService.editState(id);
        model.addAttribute("state", state);
        return "parameter/stateDetails";
    }
}
