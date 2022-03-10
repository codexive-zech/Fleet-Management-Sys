package com.zechariah.fleetms.parameter.controllers;

import com.zechariah.fleetms.parameter.models.Supplier;
import com.zechariah.fleetms.parameter.services.CountryService;
import com.zechariah.fleetms.parameter.services.StateService;
import com.zechariah.fleetms.parameter.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    public String getSuppliers(Model model, String keyword){
        //        Declaring Supplier List
        List<Supplier> suppliers;
        //    Checking to see if Keyword is null or not.
        if (keyword == null){
            suppliers = supplierService.getAllSuppliers();
        } else {
            suppliers = supplierService.findByKeyword(keyword);
        }
        //      Displaying Supplier List in the web Page
        getModel(model);
        model.addAttribute("suppliers", suppliers);
        return "/parameter/supplierList";
    }

    @GetMapping("supplierAdd")
    public String getAddSupplier(Model model){
        model.addAttribute("countries", countryService.getAllCountry());
        return "/parameter/supplierAdd";
    }

    @PostMapping("/suppliers")
    public String saveSupplier(Supplier supplier, RedirectAttributes redirectAttributes){
        supplierService.saveSupplier(supplier);
        redirectAttributes.addFlashAttribute("message" ,"Supplier Has Been Added Successfully");
        return "redirect:/suppliers";
    }

    @RequestMapping(value = "/suppliers/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteSupplier(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        supplierService.deleteSupplier(id);
        redirectAttributes.addFlashAttribute("message", "Supplier Has Been Deleted Successfully");
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
    public String editSupplier(Supplier supplier, RedirectAttributes redirectAttributes){
        supplierService.saveSupplier(supplier);
        redirectAttributes.addFlashAttribute("message", "Supplier has Been Deleted Successfully");
        return "redirect:/suppliers";
    }

    @GetMapping("/supplierDetails/{id}")
    public String displayingSuppliers(@PathVariable Integer id, Model model){
        Supplier supplier = supplierService.editSupplier(id);
        model.addAttribute("supplier", supplier);
        return "/parameter/supplierDetails";
    }
}
