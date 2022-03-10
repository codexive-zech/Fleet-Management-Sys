package com.zechariah.fleetms.parameter.controllers;

import com.zechariah.fleetms.parameter.models.Contact;
import com.zechariah.fleetms.parameter.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("contacts")
    public String getContacts(Model model){
        List<Contact> contacts = contactService.getContacts();
        model.addAttribute("contacts", contacts);
        return "/parameter/contactList";
    }

    @GetMapping("/contactAdd")
    public String displayAddContact(){
        return "/parameter/contactAdd";
    }

    @PostMapping("/contacts")
    public String addNewContact(Contact contact, RedirectAttributes redirectAttributes){
        contactService.saveContact(contact);
        redirectAttributes.addFlashAttribute("message", "Contact Has Been Added Successfully");
        return "redirect:/contacts";
    }

    @RequestMapping(value = "/contacts/delete/{id}", method = { RequestMethod.GET, RequestMethod.DELETE})
    public String deleteContact(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        contactService.deleteContact(id);
        redirectAttributes.addFlashAttribute("message", "Contact Has Been Deleted Successfully");
        return "redirect:/contacts";
    }

    @GetMapping("/contactEdit/{id}")
    public String editContact(@PathVariable Integer id, Model model){
        Contact contacts = contactService.editContact(id);
        model.addAttribute("contact", contacts);
        return "/parameter/contactEdit";
    }

    @RequestMapping(value = "/contacts/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editContact(Contact contact, RedirectAttributes redirectAttributes){
        contactService.saveContact(contact);
        redirectAttributes.addFlashAttribute("message", "Contact Has Been Updated Successfully");
        return "redirect:/contacts";
    }

    @GetMapping("/contactDetails/{id}")
    public String displayContact(@PathVariable Integer id, Model model){
        Contact contact = contactService.editContact(id);
        model.addAttribute("contact", contact);
        return "/parameter/contactDetails";
    }
}
