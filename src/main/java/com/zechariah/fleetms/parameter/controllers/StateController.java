package com.zechariah.fleetms.parameter.controllers;


import com.zechariah.fleetms.parameter.models.Country;
import com.zechariah.fleetms.parameter.models.State;
import com.zechariah.fleetms.parameter.services.CountryService;
import com.zechariah.fleetms.parameter.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String viewStates(Model model){
        getModel(model);
        return "/parameter/stateList";
    }

    @GetMapping("/stateAdd")
    public String getNewState(Model model){
        getModel(model);
        return "/parameter/stateAdd";
    }

    @PostMapping("/states")
    public String addState(State state){
        stateService.saveState(state);
        return "redirect:/states";
    }

    @RequestMapping(value = "/states/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteState(@PathVariable Integer id){
        stateService.deleteState(id);
        return "redirect:/states";
    }

    @GetMapping("stateEdit/{id}")
    public String getEditState(@PathVariable Integer id, Model model){
        getModel(model);
        model.addAttribute("state", stateService.editState(id));
        return "parameter/stateEdit";
    }

    @RequestMapping(value = "/state/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editState(State state){
        stateService.saveState(state);
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
