package com.zechariah.fleetms.parameter.repositories;

import com.zechariah.fleetms.parameter.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
