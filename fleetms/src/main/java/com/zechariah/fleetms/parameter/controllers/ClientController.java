package com.zechariah.fleetms.parameter.controllers;

import com.zechariah.fleetms.parameter.models.Client;
import com.zechariah.fleetms.parameter.services.ClientService;
import com.zechariah.fleetms.parameter.services.CountryService;
import com.zechariah.fleetms.parameter.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private StateService stateService;

    @Autowired
    private CountryService countryService;

    public Model getModels(Model model){
        model.addAttribute("clients", clientService.getClients());
        model.addAttribute("states", stateService.getStates());
        model.addAttribute("countries", countryService.getAllCountry());
        return model;
    }

    @GetMapping("/clients")
    public String getClient(Model model, String keyword){
        //        Declaring Client List
        List<Client> clients;

        //    Checking to see if Keyword is null or not.
        if (keyword == null){
            clients = clientService.getClients();
        } else {
           clients = clientService.findByKeyword(keyword);
        }

        //      Displaying Client List in the web Page
        getModels(model);
        model.addAttribute("clients", clients);
        return "/parameter/clientList";
    }

    @GetMapping("/clientAdd")
    public String getClientAdd(Model model){
        model.addAttribute("countries", countryService.getAllCountry());
        return "/parameter/clientAdd";
    }

    @PostMapping("/clients")
    public String saveClient(Client client, RedirectAttributes redirectAttributes){
        clientService.saveClient(client);
        redirectAttributes.addFlashAttribute("message", "Client Has Been Added Successfully");
        return "redirect:/clients";
    }

    @RequestMapping(value = "/clients/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteClient(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        clientService.deleteClient(id);
        redirectAttributes.addFlashAttribute("message", "Client Has Benn Deleted Successfully.");
        return "redirect:/clients";
    }

    @GetMapping("/clientEdit/{id}")
    public String getClientEdit(@PathVariable Integer id, Model model){
        getModels(model);
        Client client = clientService.editClient(id);
        model.addAttribute("client", client);
        return "/parameter/clientEdit";
    }

    @RequestMapping(value = "/client/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editClient(Client client, RedirectAttributes redirectAttributes){
        clientService.saveClient(client);
        redirectAttributes.addFlashAttribute("message", "Client Has Been Updated successfully.");
        return "redirect:/clients";
    }

    @GetMapping("/clientDetails/{id}")
    public String getClientDetails(@PathVariable Integer id, Model model){
        Client client = clientService.editClient(id);
        model.addAttribute("client", client);
        return "/parameter/clientDetails";
    }
}
