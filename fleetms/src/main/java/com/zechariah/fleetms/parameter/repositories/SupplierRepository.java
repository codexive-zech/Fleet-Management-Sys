package com.zechariah.fleetms.parameter.repositories;

import com.zechariah.fleetms.parameter.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    //    Search Filter Supplier Table
    @Query(value = "select s from Supplier s where " +
        "concat(s.name, s.address, s.city, s.phone, s.email) like %?1%")
    List<Supplier> findByKeyword(String keyword);
}
