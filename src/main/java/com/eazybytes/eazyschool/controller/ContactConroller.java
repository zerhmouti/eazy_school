package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.service.ContactService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ContactConroller {
    private final ContactService contactService ;

    @Autowired
    public ContactConroller(ContactService contactService){
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage(Model model){
        model.addAttribute("contact", new Contact()) ;
        return "contact.html" ;
    }

//    @PostMapping ( "/saveMsg")
//    public ModelAndView saveMsage(@RequestParam String name, @RequestParam String mobileNum,
//                                  @RequestParam String email, @RequestParam String subject, @RequestParam String message){
//        log.info("Name: "+ name);
//        log.info("Mobile phone: "+ mobileNum);
//        return new ModelAndView("redirect:/contact") ;
//    }

    @PostMapping("/saveMsg")
    public String saveMessage(@ModelAttribute("contact") @Valid Contact contact, Errors errors){
        if(errors.hasErrors()){
            log.error("an error has occured due to :"+ errors.toString()) ;
            return "contact" ;
        }
        contactService.saveMessageDeatails(contact) ;

        return "redirect:/contact";
    }

//    @RequestMapping("/displayMessages")
//    public ModelAndView displayMessages(Model model){
//        ModelAndView modelAndView = new ModelAndView("messages.html");
//        List<Contact> contacts= contactService.findMsgsWithOpenStatus();
//        modelAndView.addObject("contactMsgs",contacts);
//        return modelAndView;
//    }
    @RequestMapping("displayMessages/page/{pageNum}")
    public String displayMessges(@PathVariable(name="pageNum") int pageNum, @RequestParam("sortField") String sortField,
                                 @RequestParam("sortDir") String sortDir, Model model){
        Page<Contact> msgPage =contactService.findMsgsWithOpenStatus(pageNum,sortField,sortDir);
        List<Contact> contactMesgs = msgPage.getContent();
        model.addAttribute("currentPage",pageNum);
        model.addAttribute("totalPages",msgPage.getTotalPages());
        model.addAttribute("totalMsgs",msgPage.getTotalElements());
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("contactMsgs",contactMesgs);
        return "messages";
    }

    @RequestMapping("/closeMsg")
    public String closeMsg(@RequestParam("id") int id){
        contactService.updateMsgStatus(id);
        return "redirect:/displayMessages/page/1?sortField=name&sortDir=asc";
    }
}
