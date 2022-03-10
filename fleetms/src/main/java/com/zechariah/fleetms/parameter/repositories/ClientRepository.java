package com.zechariah.fleetms.parameter.repositories;

import com.zechariah.fleetms.parameter.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    //    Search Filter Client Table
    @Query(value = "select c from Client c where " +
                "concat(c.name, c.address, c.city, c.email) like %?1%")
    List<Client> findByKeyword(String keyword);
}
