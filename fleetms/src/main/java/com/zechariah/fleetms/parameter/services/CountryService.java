package com.zechariah.fleetms.parameter.services;

import com.zechariah.fleetms.parameter.models.Country;
import com.zechariah.fleetms.parameter.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountry() {
        return countryRepository.findAll();
    }

    public void saveCountry(Country country) {
        countryRepository.save(country);
    }

    public void deleteCountry(Integer id) {
        countryRepository.deleteById(id);
    }

    public Country editCountry(Integer id) {
        return countryRepository.findById(id).orElse(null);
    }

    //    Search Filter Country Table
    public List<Country> findByKeyword(String keyword) {
        return countryRepository.findByKeyword(keyword);
    }

//        Sort the Country Table
    public List<Country> getAllCountryWithSort(String field){
        return countryRepository.findAll(Sort.by(field));
    }

//    Country Pagination
    public Page<Country> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return countryRepository.findAll(pageable);
    }
}


