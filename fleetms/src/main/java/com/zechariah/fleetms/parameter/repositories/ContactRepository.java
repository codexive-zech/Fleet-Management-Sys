package com.zechariah.fleetms.parameter.repositories;

import com.zechariah.fleetms.parameter.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    //    Search Filter Contact Table
    @Query(value = "select c from Contact c where " +
            "concat(c.firstname, c.lastname, c.phone, c.email) like %?1%")
    List<Contact> findByKeyword(String keyword);
}
