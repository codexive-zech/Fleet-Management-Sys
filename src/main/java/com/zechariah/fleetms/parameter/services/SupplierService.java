package com.zechariah.fleetms.parameter.services;

import com.zechariah.fleetms.parameter.models.Supplier;
import com.zechariah.fleetms.parameter.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers(){
        return supplierRepository.findAll();
    }

    public void saveSupplier(Supplier supplier){
        supplierRepository.save(supplier);
    }

    public void deleteSupplier(Integer id){
        supplierRepository.deleteById(id);
    }

    public Supplier editSupplier(Integer id){
        return supplierRepository.findById(id).orElse(null);
    }
}
