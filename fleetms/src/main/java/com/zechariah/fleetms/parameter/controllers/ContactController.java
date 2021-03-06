package com.zechariah.fleetms.parameter.controllers;

import com.zechariah.fleetms.parameter.models.Client;
import com.zechariah.fleetms.parameter.models.Contact;
import com.zechariah.fleetms.parameter.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

//    //    Displaying the List of Contact in the webpage
//    @GetMapping("contacts")
//    public String getContacts(Model model, String keyword){
//        //        Declaring Contact List
//        List<Contact> contacts;
//
//        //    Checking to see if Keyword is null or not.
//        if (keyword == null){
//            contacts = contactService.getContacts();
//        } else {
//            contacts = contactService.findByKeyword(keyword);
//        }
//
//        //      Displaying Contact List in the web Page
//        model.addAttribute("contacts", contacts);
//        return "/parameter/contactList";
//    }

    @GetMapping("/contacts")
    public String getAllPages(Model model){
        return getOneContacts(model, 1);
    }

    //    This endpoint will be triggered when no page number is given so this is the first page available
    @GetMapping("/contacts/page/{pageNumber}")
    public String getOneContacts(Model model, @PathVariable("pageNumber") int currentPage){
        //        Getting the Pageable Countries
        Page<Contact> page = contactService.findPage(currentPage);
        //        Retrieving The Total Number of Pages Available
        int totalPages = page.getTotalPages();
        //        Retrieving The Total Number of Pages Available
        long totalItems = page.getTotalElements();
        //        Retrieving The Total Number of Pages Available
        List<Contact> contacts = page.getContent();
        //      Displaying Contact List in the web Page
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("contacts", contacts);
        return "/parameter/contactList";
    }

//    Displaying Contact Sorting and Paging into the webpage
@GetMapping("/contacts/page/{pageNumber}/{field}")
public String getPageWithSort(Model model,
                              @PathVariable("pageNumber") int currentPage,
                              @PathVariable String field,
                              @PathParam("sortDir") String sortDir){

//        Getting the Contact Sorting and page available from the service
    Page<Contact> page = contactService.findClientWithSorting(field, sortDir, currentPage);

//        Display the list of Contact in a Page Format
    List<Contact> contacts = page.getContent();
    //        Retrieving The Total Number of Pages Available
    int totalPages = page.getTotalPages();
    //        Retrieving The Total Number of Items Available
    long totalItems = page.getTotalElements();

    //      Displaying Contact List in the web Page
    model.addAttribute("currentPage", currentPage);
    model.addAttribute("totalPages", totalPages);
    model.addAttribute("totalItems", totalItems);
    model.addAttribute("sortDir", sortDir);
    model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
    model.addAttribute("contacts", contacts);
    return "/parameter/contactList";
}

    //    Displaying the Webpage of Add Form for Contact
    @GetMapping("/contactAdd")
    public String displayAddContact(){
        return "/parameter/contactAdd";
    }

    //    Saving the Information in the Form to the Database and Displaying the List back
    @PostMapping("/contacts")
    public String addNewContact(Contact contact, RedirectAttributes redirectAttributes){
        //        Calling on to the save method for Contact in other to carry out Post Request
        contactService.saveContact(contact);
        //        Sending back a Notification in the webpage when a Contact has been saved
        redirectAttributes.addFlashAttribute("message", "Contact Has Been Added Successfully");
        return "redirect:/contacts";
    }

    //    Deleting and Displaying a Contact in the webpage
    @RequestMapping(value = "/contacts/delete/{id}", method = { RequestMethod.GET, RequestMethod.DELETE})
    public String deleteContact(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        //        Calling on to the delete method for Contact in other to carry out Delete Request
        contactService.deleteContact(id);
        //        Sending back a Notification in the webpage when a Contact has been deleted
        redirectAttributes.addFlashAttribute("message", "Contact Has Been Deleted Successfully");
        return "redirect:/contacts";
    }

    //    Display a particular Contact to edit
    @GetMapping("/contactEdit/{id}")
    public String editContact(@PathVariable Integer id, Model model){
        //        Calling on to the edit method for Contact in other to carry out Put Request
        Contact contacts = contactService.editContact(id);
        //        Displaying the Contact back to the Webpage
        model.addAttribute("contact", contacts);
        return "/parameter/contactEdit";
    }

    //    Saving a particular Contact after edit and Displaying to the webpage
    @RequestMapping(value = "/contacts/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editContact(Contact contact, RedirectAttributes redirectAttributes){
        //        Calling on to the save method for Contact in other to carry out Post Request
        contactService.saveContact(contact);
        //        Sending back a Notification in the webpage when a Contact has been Updated
        redirectAttributes.addFlashAttribute("message", "Contact Has Been Updated Successfully");
        return "redirect:/contacts";
    }

    //    Displaying Contact Details on the webpage
    @GetMapping("/contactDetails/{id}")
    public String displayContact(@PathVariable Integer id, Model model){
        //        Calling on to the edit method for Contact in other to carry out Put Request
        Contact contact = contactService.editContact(id);
        //        Displaying the Contact back to the Webpage
        model.addAttribute("contact", contact);
        return "/parameter/contactDetails";
    }
}
