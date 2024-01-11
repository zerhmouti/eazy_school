package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.Constants.EazySchoolConstants;
import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.model.Roles;
import com.eazybytes.eazyschool.repository.PersonRepository;
import com.eazybytes.eazyschool.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private PersonRepository personRepository ;
    @Autowired
    private RolesRepository rolesRepository ;
    public boolean createNewPerson(Person person){
        boolean isSaved= false ;
        Roles roles= rolesRepository.getByRoleName(EazySchoolConstants.STUDENT_ROLE);
        person.setRoles(roles);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person =personRepository.save(person) ;
        //to check if it was persisted in DB
        if(person!=null && person.getPersonId()>0){
            isSaved= true;
        }
        return isSaved ;
    }

}
