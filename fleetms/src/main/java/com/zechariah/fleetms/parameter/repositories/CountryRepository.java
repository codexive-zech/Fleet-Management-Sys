package com.zechariah.fleetms.parameter.repositories;

import com.zechariah.fleetms.parameter.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

//    Search Filter Country Table
    @Query(value = "select c from Country c where " +
                    "concat(c.description, c.capital, c.code, c.nationality, c.continent) like %?1%")
    List<Country> findByKeyword(String keyword);
}
