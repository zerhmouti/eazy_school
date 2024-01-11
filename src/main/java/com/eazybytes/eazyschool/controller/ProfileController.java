package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Address;
import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.model.Profile;
import com.eazybytes.eazyschool.repository.AddressRepository;
import com.eazybytes.eazyschool.repository.PersonRepository;
import com.eazybytes.eazyschool.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
public class ProfileController {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    AddressRepository addressRepository;
    @RequestMapping("/displayProfile")
    public ModelAndView displayProfile(Model model, Authentication authentication, HttpSession session){
        Person person= (Person)session.getAttribute("loggedInPerson");
        Profile profile = new Profile();
        profile.setName(person.getName());
        profile.setEmail(person.getEmail());
        profile.setMobileNumber(person.getMobileNumber());
        if(person.getAddress()!= null && person.getPersonId()>0){
            profile.setAddress1(person.getAddress().getAddress1());
            profile.setAddress2(person.getAddress().getAddress2());
            profile.setCity(person.getAddress().getCity());
            profile.setState(person.getAddress().getState());
            profile.setZipCode(person.getAddress().getZipCode()+"");
        }
        ModelAndView modelAndView=new ModelAndView("profile");
        modelAndView.addObject(profile);
        return  modelAndView;
    }
    @PostMapping("/updateProfile")
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errrors, HttpSession httpSession){
        if(errrors.hasErrors()){
            return "profile";
        }
        Person person= (Person)httpSession.getAttribute("loggedInPerson");
        person.setEmail(profile.getEmail());
        person.setName(profile.getName());
        person.setMobileNumber(profile.getMobileNumber());
        if(person.getAddress()==null ||person.getAddress().getAddressId()>0) {
            person.setAddress(new Address());
        }
        person.getAddress().setAddress1(profile.getAddress1());
        person.getAddress().setAddress2(profile.getAddress2());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipCode(Integer.parseInt(profile.getZipCode()));
        httpSession.setAttribute("loggedInPerson",person);
        personRepository.save(person);
        return "redirect:/displayProfile";
    }
}
