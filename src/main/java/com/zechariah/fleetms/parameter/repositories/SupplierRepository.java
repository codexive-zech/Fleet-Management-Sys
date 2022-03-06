package com.zechariah.fleetms.parameter.repositories;

import com.zechariah.fleetms.parameter.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
