package com.zechariah.fleetms.parameter.controllers;

import com.zechariah.fleetms.parameter.models.Client;
import com.zechariah.fleetms.parameter.services.ClientService;
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
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private StateService stateService;

    @Autowired
    private CountryService countryService;

    //    Getting all The Details meant to be rendered on the webpage
    public Model getModels(Model model){
        model.addAttribute("clients", clientService.getClients());
        model.addAttribute("states", stateService.getStates());
        model.addAttribute("countries", countryService.getAllCountry());
        return model;
    }

    //    Displaying the List of Client in the webpage
//    @GetMapping("/clients")
//    public String getClient(Model model, String keyword){
//        //        Declaring Client List
//        List<Client> clients;
//
//        //    Checking to see if Keyword is null or not.
//        if (keyword == null){
//            clients = clientService.getClients();
//        } else {
//           clients = clientService.findByKeyword(keyword);
//        }
//
//        //      Displaying Client List in the web Page
//        getModels(model);
//        model.addAttribute("clients", clients);
//        return "/parameter/clientList";
//    }

    //    This endpoint will be triggered when no page number is given so this is the first page available
    @GetMapping("/clients")
    public String getAllPages(Model model){
        return getOnePage(model, 1);
    }

    //    Displaying the List of Country in the webpage via Page
    @GetMapping("/clients/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
        //        Getting the Pageable Countries
        Page<Client> page = clientService.findPage(currentPage);
        //        Retrieving The Total Number of Pages Available
        int totalPages = page.getTotalPages();
        //        Retrieving The Total Number of Pages Available
        long totalItems = page.getTotalElements();
        //        Retrieving the total list of countries available
        List<Client> clients = page.getContent();
        //      Displaying Client List in the web Page
        getModels(model);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("clients", clients);
        return "/parameter/clientList";
    }

    //    Displaying Client Sorting into the webpage
    @GetMapping("/clients/{field}")
    public String getClientWithSort(Model model, @PathVariable("field") String field) {
        //        Declaring Client List
        List<Client> clients;

        //    Sorting the Clients Table via Field
        clients = clientService.getClientsWithSort(field);

        //      Displaying Client List in the web Page
        getModels(model);
        model.addAttribute("clients", clients);
        return "/parameter/clientList";
    }

    //    Displaying the Webpage of Add Form for Client
    @GetMapping("/clientAdd")
    public String getClientAdd(Model model){
        model.addAttribute("countries", countryService.getAllCountry());
        return "/parameter/clientAdd";
    }

    //    Saving the Information in the Form to the Database and Displaying the List back
    @PostMapping("/clients")
    public String saveClient(Client client, RedirectAttributes redirectAttributes){
        clientService.saveClient(client);
        redirectAttributes.addFlashAttribute("message", "Client Has Been Added Successfully");
        return "redirect:/clients";
    }

    //    Deleting and Displaying a Client in the webpage
    @RequestMapping(value = "/clients/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteClient(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        clientService.deleteClient(id);
        redirectAttributes.addFlashAttribute("message", "Client Has Benn Deleted Successfully.");
        return "redirect:/clients";
    }

    //    Display a particular Client to edit
    @GetMapping("/clientEdit/{id}")
    public String getClientEdit(@PathVariable Integer id, Model model){
        getModels(model);
        Client client = clientService.editClient(id);
        model.addAttribute("client", client);
        return "/parameter/clientEdit";
    }

    //    Saving a particular Client after edit and Displaying to the webpage
    @RequestMapping(value = "/client/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editClient(Client client, RedirectAttributes redirectAttributes){
        clientService.saveClient(client);
        redirectAttributes.addFlashAttribute("message", "Client Has Been Updated successfully.");
        return "redirect:/clients";
    }

    //    Displaying Client Details on the webpage
    @GetMapping("/clientDetails/{id}")
    public String getClientDetails(@PathVariable Integer id, Model model){
        Client client = clientService.editClient(id);
        model.addAttribute("client", client);
        return "/parameter/clientDetails";
    }
}
