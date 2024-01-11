package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.repository.CoursesRepository;
import com.eazybytes.eazyschool.repository.EazyClassRepository;
import com.eazybytes.eazyschool.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("student")
public class StudentController {
    @Autowired
    EazyClassRepository eazyClassRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    CoursesRepository coursesRepository;
    @GetMapping("/displayCourses")
    public String displayCourses(Model model, HttpSession session){
        Person person= (Person)session.getAttribute("loggedInPerson");
        model.addAttribute("person",person);
        return "courses_enrolled.html";
    }



}
