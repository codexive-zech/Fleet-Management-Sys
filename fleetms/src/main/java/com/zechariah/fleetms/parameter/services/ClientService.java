package com.zechariah.fleetms.parameter.services;

import com.zechariah.fleetms.parameter.models.Client;
import com.zechariah.fleetms.parameter.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getClients(){
        return clientRepository.findAll();
    }

    public void saveClient(Client client){
        clientRepository.save(client);
    }

    public void deleteClient(Integer id){
        clientRepository.deleteById(id);
    }

    public Client editClient(Integer id){
        return clientRepository.findById(id).orElse(null);
    }

    //    Search Filter Client Table
    public List<Client> findByKeyword(String keyword){
        return clientRepository.findByKeyword(keyword);
    }

    //        Sort the Client Table
    public Page<Client> findClientWithSorting(String field, String direction, int pageNumber){
//        Sorting Client By Direction with an If Statement
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
//        Adding the Sorted Content into making it Pageable
        Pageable pageable = PageRequest.of(pageNumber - 1,5, sort);
        return clientRepository.findAll(pageable);
    }

//    Pagination
    public Page<Client> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return clientRepository.findAll(pageable);
    }
}
