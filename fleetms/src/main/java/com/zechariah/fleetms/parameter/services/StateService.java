package com.zechariah.fleetms.parameter.services;

import com.zechariah.fleetms.parameter.models.Client;
import com.zechariah.fleetms.parameter.models.State;
import com.zechariah.fleetms.parameter.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
