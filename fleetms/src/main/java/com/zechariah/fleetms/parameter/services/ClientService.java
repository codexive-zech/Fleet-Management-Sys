package com.zechariah.fleetms.parameter.services;

import com.zechariah.fleetms.parameter.models.Client;
import com.zechariah.fleetms.parameter.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
