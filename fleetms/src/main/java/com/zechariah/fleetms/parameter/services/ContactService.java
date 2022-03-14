package com.zechariah.fleetms.parameter.services;

import com.zechariah.fleetms.parameter.models.Client;
import com.zechariah.fleetms.parameter.models.Contact;
import com.zechariah.fleetms.parameter.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Contact> findClientWithSorting(String field, String direction, int pageNumber){
//        Sorting Contact By Direction with an If Statement
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
//        Adding the Sorted Content into making it Pageable
        Pageable pageable = PageRequest.of(pageNumber - 1,5, sort);
        return contactRepository.findAll(pageable);
    }
//    Pagination
    public Page<Contact> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return contactRepository.findAll(pageable);
    }
}
