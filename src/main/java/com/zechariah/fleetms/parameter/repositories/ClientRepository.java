package com.zechariah.fleetms.parameter.repositories;

import com.zechariah.fleetms.parameter.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
