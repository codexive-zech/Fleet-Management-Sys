package com.zechariah.fleetms.parameter.services;

import com.zechariah.fleetms.parameter.models.Supplier;
import com.zechariah.fleetms.parameter.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    //    Search Filter Supplier Table
    public List<Supplier> findByKeyword(String keyword){
        return supplierRepository.findByKeyword(keyword);
    }

    //        Sort the Supplier Table
    public List<Supplier> getAllSuppliersWithSort(String field){
        return supplierRepository.findAll(Sort.by(field));
    }

    public Page<Supplier> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return supplierRepository.findAll(pageable);
    }
}
