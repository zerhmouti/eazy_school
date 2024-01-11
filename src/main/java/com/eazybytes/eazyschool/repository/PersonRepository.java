package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    public Person getPersonByName(String name);
    public Person getPersonByEmail(String email);
}
