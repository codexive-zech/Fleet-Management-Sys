package com.zechariah.fleetms.parameter.controllers;


import com.zechariah.fleetms.parameter.models.Client;
import com.zechariah.fleetms.parameter.models.Country;
import com.zechariah.fleetms.parameter.models.State;
import com.zechariah.fleetms.parameter.services.CountryService;
import com.zechariah.fleetms.parameter.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Model getModel(Model model){
        model.addAttribute("states", stateService.getStates());
        model.addAttribute("countries", countryService.getAllCountry());
        return model;
    }


    @GetMapping("/states")
    public String viewStates(Model model, String keyword){
        //    Declaring State List
        List<State> states;
        //    Checking to see if Keyword is null or not.
        if (keyword == null){
            states = stateService.getStates();
        } else {
           states = stateService.findByKeyword(keyword);
        }
        //    Displaying State List in the web Page
        getModel(model);
        model.addAttribute("states", states);
        return "/parameter/stateList";
    }
    
    @GetMapping("/stateAdd")
    public String getNewState(Model model){
        getModel(model);
        return "/parameter/stateAdd";
    }

    @PostMapping("/states")
    public String addState(State state, RedirectAttributes redirectAttributes){
        stateService.saveState(state);
        redirectAttributes.addFlashAttribute("message", "State Has Been Added Successfully");
        return "redirect:/states";
    }

    @RequestMapping(value = "/states/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteState(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        stateService.deleteState(id);
        redirectAttributes.addFlashAttribute("message", "State Has Been Deleted Successfully");
        return "redirect:/states";
    }

    @GetMapping("stateEdit/{id}")
    public String getEditState(@PathVariable Integer id, Model model){
        getModel(model);
        model.addAttribute("state", stateService.editState(id));
        return "parameter/stateEdit";
    }

    @RequestMapping(value = "/state/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editState(State state, RedirectAttributes redirectAttributes){
        stateService.saveState(state);
        redirectAttributes.addFlashAttribute("message", "State Has Been Updated Successfully");
        return "redirect:/states";
    }

//    Display State
    @GetMapping("/stateDetails/{id}")
    public String getStateDetails(@PathVariable Integer id, Model model){
        State state = stateService.editState(id);
        model.addAttribute("state", state);
        return "parameter/stateDetails";
    }
}
