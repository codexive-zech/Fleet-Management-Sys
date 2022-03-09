package com.zechariah.fleetms.parameter.controllers;

import com.zechariah.fleetms.parameter.models.Supplier;
import com.zechariah.fleetms.parameter.services.CountryService;
import com.zechariah.fleetms.parameter.services.StateService;
import com.zechariah.fleetms.parameter.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private StateService stateService;

    @Autowired
    private CountryService countryService;

    public Model getModel(Model model){
        model.addAttribute("countries", countryService.getAllCountry());
        model.addAttribute("states", stateService.getStates());
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return model;
    }

    @GetMapping("/suppliers")
    public String getSuppliers(Model model){
        getModel(model);
        return "/parameter/supplierList";
    }

    @GetMapping("supplierAdd")
    public String getAddSupplier(Model model){
        model.addAttribute("countries", countryService.getAllCountry());
        return "/parameter/supplierAdd";
    }

    @PostMapping("/suppliers")
    public String saveSupplier(Supplier supplier){
        supplierService.saveSupplier(supplier);
        return "redirect:/suppliers";
    }

    @RequestMapping(value = "/suppliers/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteSupplier(@PathVariable Integer id){
        supplierService.deleteSupplier(id);
        return "redirect:/suppliers";
    }

    @GetMapping("/supplierEdit/{id}")
    public String getEditSupplier(@PathVariable Integer id, Model model){
        getModel(model);
        Supplier supplier = supplierService.editSupplier(id);
        model.addAttribute("supplier", supplier);
        return "/parameter/supplierEdit";
    }

    @RequestMapping(value = "/supplier/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editSupplier(Supplier supplier){
        supplierService.saveSupplier(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/supplierDetails/{id}")
    public String displayingSuppliers(@PathVariable Integer id, Model model){
        Supplier supplier = supplierService.editSupplier(id);
        model.addAttribute("supplier", supplier);
        return "/parameter/supplierDetails";
    }
}
