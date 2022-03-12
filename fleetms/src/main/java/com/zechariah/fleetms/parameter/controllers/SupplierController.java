package com.zechariah.fleetms.parameter.controllers;

import com.zechariah.fleetms.parameter.models.Supplier;
import com.zechariah.fleetms.parameter.services.CountryService;
import com.zechariah.fleetms.parameter.services.StateService;
import com.zechariah.fleetms.parameter.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

//    Getting all The Details meant to be rendered on the webpage
    public Model getModel(Model model){
        model.addAttribute("countries", countryService.getAllCountry());
        model.addAttribute("states", stateService.getStates());
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return model;
    }

//    Displaying the List of Supplier in the webpage
//    @GetMapping("/suppliers")
//    public String getSuppliers(Model model, String keyword){
//        //        Declaring Supplier List
//        List<Supplier> suppliers;
//        //    Checking to see if Keyword is null or not.
//        if (keyword == null){
//            suppliers = supplierService.getAllSuppliers();
//        } else {
//            suppliers = supplierService.findByKeyword(keyword);
//        }
//        //      Displaying Supplier List in the web Page
//        getModel(model);
//        model.addAttribute("suppliers", suppliers);
//        return "/parameter/supplierList";
//    }

    //    This endpoint will be triggered when no page number is given so this is the first page available
    @GetMapping("/suppliers")
    public String getSuppliers(Model model){
        return getOneSuppliers(model, 1);
    }

    //    Displaying the List of Country in the webpage via Page
    @GetMapping("/suppliers/page/{pageNumber}")
    public String getOneSuppliers(Model model, @PathVariable("pageNumber") int currentPage){
        //        Getting the Pageable Countries
        Page<Supplier> page = supplierService.findPage(currentPage);
        //        Retrieving The Total Number of Pages Available
        int totalPages = page.getTotalPages();
        //        Retrieving The Total Number of Pages Available
        long totalItems = page.getTotalElements();
        //        Retrieving the total list of countries available
        List<Supplier> suppliers = page.getContent();
        //      Displaying Supplier List in the web Page
        getModel(model);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("suppliers", suppliers);
        return "/parameter/supplierList";
    }
//    **********************

//    Displaying Supplier Sorting into the webpage
    @GetMapping("/suppliers/{field}")
    public String getSuppliersWithSort(Model model, @PathVariable String field){
        //        Declaring Supplier List
        List<Supplier> suppliers;
        //    Sorting the Supplier Table via Field
        suppliers = supplierService.getAllSuppliersWithSort(field);
        //      Displaying Supplier List in the web Page
        getModel(model);
        model.addAttribute("suppliers", suppliers);
        return "/parameter/supplierList";
    }

//    Displaying the Webpage of Add Form for Supplier
    @GetMapping("supplierAdd")
    public String getAddSupplier(Model model){
        model.addAttribute("countries", countryService.getAllCountry());
        return "/parameter/supplierAdd";
    }

//    Saving the Information in the Form to the Database and Displaying the List back
    @PostMapping("/suppliers")
    public String saveSupplier(Supplier supplier, RedirectAttributes redirectAttributes){
        supplierService.saveSupplier(supplier);
        redirectAttributes.addFlashAttribute("message" ,"Supplier Has Been Added Successfully");
        return "redirect:/suppliers";
    }

//    Deleting and Displaying a Supplier in the webpage
    @RequestMapping(value = "/suppliers/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteSupplier(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        supplierService.deleteSupplier(id);
        redirectAttributes.addFlashAttribute("message", "Supplier Has Been Deleted Successfully");
        return "redirect:/suppliers";
    }

//    Display a particular supplier to edit
    @GetMapping("/supplierEdit/{id}")
    public String getEditSupplier(@PathVariable Integer id, Model model){
        getModel(model);
        Supplier supplier = supplierService.editSupplier(id);
        model.addAttribute("supplier", supplier);
        return "/parameter/supplierEdit";
    }

//    Saving a particular Supplier after edit and Displaying to the webpage
    @RequestMapping(value = "/supplier/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editSupplier(Supplier supplier, RedirectAttributes redirectAttributes){
        supplierService.saveSupplier(supplier);
        redirectAttributes.addFlashAttribute("message", "Supplier has Been Deleted Successfully");
        return "redirect:/suppliers";
    }

//    Displaying Suppliers Details on the webpage
    @GetMapping("/supplierDetails/{id}")
    public String displayingSuppliers(@PathVariable Integer id, Model model){
        Supplier supplier = supplierService.editSupplier(id);
        model.addAttribute("supplier", supplier);
        return "/parameter/supplierDetails";
    }
}
