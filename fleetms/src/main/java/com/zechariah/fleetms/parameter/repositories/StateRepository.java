package com.zechariah.fleetms.parameter.repositories;

import com.zechariah.fleetms.parameter.models.Client;
import com.zechariah.fleetms.parameter.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

    //    Search Filter State Table
    @Query(value = "select s from State s where " +
            "concat(s.name, s.capital) like %?1%")
    List<State> findByKeyword(String keyword);

}
