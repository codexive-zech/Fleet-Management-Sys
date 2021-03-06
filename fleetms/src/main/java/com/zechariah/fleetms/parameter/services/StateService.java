package com.zechariah.fleetms.parameter.services;

import com.zechariah.fleetms.parameter.models.Client;
import com.zechariah.fleetms.parameter.models.State;
import com.zechariah.fleetms.parameter.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public List<State> getStates(){
        return stateRepository.findAll();
    }

    public void saveState(State state){
        stateRepository.save(state);
    }

    public void deleteState(Integer id) {
        stateRepository.deleteById(id);
    }

    public State editState(Integer id){
        return stateRepository.findById(id).orElse(null);
    }

    //    Search Filter State Table
    public List<State> findByKeyword(String keyword){
        return stateRepository.findByKeyword(keyword);
    }

    //        Sort the State Table
    public Page<State> findClientWithSorting(String field, String direction, int pageNumber){
//        Sorting Client By Direction with an If Statement
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
//        Adding the Sorted Content into making it Pageable
        Pageable pageable = PageRequest.of(pageNumber - 1,5, sort);
        return stateRepository.findAll(pageable);
    }

    public Page<State> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return stateRepository.findAll(pageable);
    }
}
