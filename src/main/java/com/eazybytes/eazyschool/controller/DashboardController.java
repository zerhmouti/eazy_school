package com.eazybytes.eazyschool.controller;
import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class DashboardController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping(value = "/dashboard", method ={ RequestMethod.GET,RequestMethod.POST})
    public String displayDashboard(Model model, Authentication authentication, HttpSession session) {
        Person person=personRepository.getPersonByEmail(authentication.getName());
        session.setAttribute("loggedInPerson",person);
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        if(null!= person.getEazyClass() && null!= person.getEazyClass().getName()){
            model.addAttribute("enrolledClass", person.getEazyClass());
        }
        return "dashboard.html";
    }

}