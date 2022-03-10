package com.zechariah.fleetms.parameter.repositories;

import com.zechariah.fleetms.parameter.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    //    Search Filter Location Table
    @Query(value = "select l from Location l where " +
            "concat(l.description, l.city, l.address) like %?1%")
    List<Location> findByKeyword(String keyword);
}
