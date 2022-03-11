package com.zechariah.fleetms.parameter.services;

import com.zechariah.fleetms.parameter.models.Contact;
import com.zechariah.fleetms.parameter.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getContacts(){
        return contactRepository.findAll();
    }

    public void saveContact(Contact contact){
        contactRepository.save(contact);
    }

    public void deleteContact(Integer id){
        contactRepository.deleteById(id);
    }

    public Contact editContact(Integer id){
        return contactRepository.findById(id).orElse(null);
    }

    //    Search Filter Contact Table
    public List<Contact> findByKeyword(String keyword){
        return contactRepository.findByKeyword(keyword);
    }

    //        Sort the Contact Table
    public List<Contact> getContactsWithSort(String field){
        return contactRepository.findAll(Sort.by(field));
    }
}
