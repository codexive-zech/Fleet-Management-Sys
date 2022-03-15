package com.zechariah.fleetms.fleet.controller;

import com.zechariah.fleetms.fleet.model.Vehicle;
import com.zechariah.fleetms.fleet.service.VehicleService;
import com.zechariah.fleetms.parameter.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

//    @GetMapping("/vehicles")
//    public String getAllVehicle(Model model){
//        List<Vehicle> vehicles = vehicleService.getAllVehicle();
//        model.addAttribute("vehicles", vehicles);
//        return "/fleet/vehicleList";
//    }

    @GetMapping("/vehicles")
    public String getAllPages(Model model){
        return getOnePage(1, model);
    }

    @GetMapping("/vehicles/page/{pageNumber}")
    public String getOnePage(@PathVariable (name = "pageNumber") int currentPage, Model model){
        Page<Vehicle> page = vehicleService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Vehicle> vehicles = page.getContent();

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("vehicles", vehicles);
        return "/fleet/vehicleList";
    }

    //    Displaying Client Sorting and Paging into the webpage
    @GetMapping("/vehicles/page/{pageNumber}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable("pageNumber") int currentPage,
                                  @PathVariable String field,
                                  @PathParam("sortDir") String sortDir){

//        Getting the Client Sorting and page available from the service
        Page<Vehicle> page = vehicleService.findClientWithSorting(field, sortDir, currentPage);

//        Display the list of Client in a Page Format
        List<Vehicle> vehicles = page.getContent();
        //        Retrieving The Total Number of Pages Available
        int totalPages = page.getTotalPages();
        //        Retrieving The Total Number of Items Available
        long totalItems = page.getTotalElements();

        //      Displaying Client List in the web Page
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("vehicles", vehicles);
        return "/fleet/vehicleList";
    }

    @GetMapping("/vehicleAdd")
    public String VehicleForm(){
        return "/fleet/vehicleAdd";
    }

    @PostMapping("/vehicles")
    public String addVehicle(Vehicle vehicle, RedirectAttributes redirectAttributes){
        vehicleService.saveVehicle(vehicle);
        redirectAttributes.addFlashAttribute("message", "Vehicle has Been Added Successfully");
        return "redirect:/vehicles";
    }

    @RequestMapping(value = "/vehicles/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteVehicle(@PathVariable int id, RedirectAttributes redirectAttributes){
        vehicleService.deleteVehicle(id);
        redirectAttributes.addFlashAttribute("message", "Vehicle has Been Deleted Successfully");
        return "redirect:/vehicles";
    }

    @GetMapping("/vehicleEdit/{id}")
    public String showEditForm(Model model, @PathVariable int id){
        Vehicle vehicle = vehicleService.editVehicle(id);
        model.addAttribute("vehicle", vehicle);
        return "fleet/vehicleEdit";
    }

    @RequestMapping(value = "/vehicle/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editVehicle(Vehicle vehicle, RedirectAttributes redirectAttributes){
        vehicleService.saveVehicle(vehicle);
        redirectAttributes.addFlashAttribute("message", "Vehicle Has Been Updated Successfully");
        return "redirect:/vehicles";
    }

    @GetMapping("/vehicleDetails/{id}")
    public String displayVehicle(@PathVariable int id, Model model){
        Vehicle vehicle = vehicleService.editVehicle(id);
        model.addAttribute("vehicle", vehicle);
        return "/fleet/vehicleDetails";
    }
}
